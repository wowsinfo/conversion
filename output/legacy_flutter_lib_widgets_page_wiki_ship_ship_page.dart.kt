import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:wowsinfo/foundation/app.dart';
import 'package:wowsinfo/foundation/helpers/utils.dart';
import 'package:wowsinfo/providers/wiki/ship_provider.dart';
import 'package:wowsinfo/localisation/localisation.dart';
import 'package:wowsinfo/widgets/animation/popup_box.dart';
import 'package:wowsinfo/widgets/shared/wiki/ship_cell.dart';

import 'ship_info_page.dart';

class ShipPage : StatefulWidget {
  const ShipPage({Key? key, this.special = false}) : super(key = key)

  final bool special;

  override fun onCreate(savedInstanceState: Bundle?): View {
    return super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return inflater.inflate(R.layout.fragment_ship_page, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val itemCount = Utils.of(requireContext()).getItemCount(8, 2, 119)

    val appBarLayout = view.findViewById<LinearLayout>(R.id.appBarLayout)
    val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
    val actionbarBackgroundImage = view.findViewById<ImageView>(R.id.actionbar_background_image)
    val searchButton = view.findViewById<Button>(R.id.search_button)
    val filterButton = view.findViewById<Button>(R.id.filter_button)

    appBarLayout.setPadding(
      0,
      requireActivity().getAppBarHeight(),
      0,
      requireActivity().getAppBarBottomOffset()
    )

    toolbar.setTitleTextColor(Color(0xFF1B2A49))
    actionbarBackgroundImage.setColorFilter(Color(0x99000000), PorterDuff.Mode.SRC_ATOP)
    searchButton.setTextColor(requireContext().getColor(R.color.text_greyed_out))

    val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
    recyclerView.setHasFixedSize(true)
    recyclerView.layoutManager =
      LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

    val adapter = ShipAdapter(itemCount)
    recyclerView.adapter = adapter

    val shipProvider by lazy { Provider.of<ShipProvider>(requireActivity()) }
    searchButton.setOnClickListener {
      shipProvider.showFilter()
    }

    filterButton.text = shipProvider.filterString
    filterButton.setOnClickListener {
      shipProvider.showFilter()
    }

    view.findViewById<Button>(R.id.info_button).setOnClickListener {
      requireActivity().showInfoDialog()
    }
  }

  private fun buildGridView(itemCount: Int): RecyclerView {
    val recyclerView = RecyclerView(requireContext())
    recyclerView.setHasFixedSize(true)
    recyclerView.layoutManager =
      LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

    val adapter = ShipAdapter(itemCount)
    recyclerView.adapter = adapter

    return recyclerView
  }
}

class ShipAdapter(private val itemCount: Int) : RecyclerView.Adapter<ShipViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShipViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ship, parent, false)
    return ShipViewHolder(view)
  }

  override fun onBindViewHolder(holder: ShipViewHolder, position: Int) {
    holder.bind(position, itemCount)
  }

  override fun getItemCount(): Int {
    return provider.shipCount
  }
}

class ShipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  val imageView = itemView.findViewById<ImageView>(R.id.imageView)
  val textView = itemView.findViewById<TextView>(R.id.textView)

  init {
    itemView.setOnClickListener { view ->
      val position = adapterPosition
      if (position != RecyclerView.NO_POSITION) {
        // Perform item click action
      }
    }
  }

  fun bind(position: Int, itemCount: Int) {
    // Bind data to views
  }
}