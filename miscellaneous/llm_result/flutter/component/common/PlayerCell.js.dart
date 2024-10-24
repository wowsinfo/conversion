
class PlayerCell extends StatelessWidget {
  final dynamic item;
  final bool player;
  final bool clan;
  final double width;

  PlayerCell({required this.item, required this.player, required this.clan, required this.width});

  void pushPlayer(dynamic item) {
    // Implement navigation to player details
  }

  void pushClan(dynamic item) {
    // Implement navigation to clan details
  }

  Widget renderPlayerRight(String accountId) {
    // Implement rendering of player right side content
    return Text(accountId);
  }

  Widget renderClanRight(String clanId) {
    // Implement rendering of clan right side content
    return Text(clanId);
  }

  @override
  Widget build(BuildContext context) {
    if (player) {
      return ListTile(
        title: Text(item['nickname']),
        contentPadding: EdgeInsets.all(8.0),
        tileColor: Colors.white,
        onTap: () => pushPlayer(item),
        trailing: renderPlayerRight(item['account_id']),
      );
    } else if (clan) {
      return ListTile(
        title: Text(item['tag']),
        contentPadding: EdgeInsets.all(8.0),
        tileColor: Colors.white,
        onTap: () => pushClan(item),
        trailing: renderClanRight(item['clan_id']),
      );
    } else {
      return Text('???');
    }
  }
}


class PlayerWidget extends StatelessWidget {
  final String accountId;

  PlayerWidget({required this.accountId});

  @override
  Widget build(BuildContext context) {
    return Text(
      accountId,
      style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
    );
  }
}


class ClanWidget extends StatelessWidget {
  final String clanId;

  ClanWidget({required this.clanId});

  @override
  Widget build(BuildContext context) {
    return Text(
      clanId,
      style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold), // Adjust style as needed
    );
  }
}

  SafeAction('Statistics', {'info': item});
}

  SafeAction('ClanInfo', {'info': item});
}

void SafeAction(String action, Map<String, dynamic> data) {
  // Implement the action handling logic here
}


class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Container(
        margin: EdgeInsets.only(right: 8),
        child: Text('ID'),
      ),
    );
  }
}


class PlayerCell extends StatelessWidget {
  final String playerName;
  final String playerScore;

  PlayerCell({required this.playerName, required this.playerScore});

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(8.0),
      margin: EdgeInsets.symmetric(vertical: 4.0),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(8.0),
        boxShadow: [
          BoxShadow(
            color: Colors.grey.withOpacity(0.2),
            spreadRadius: 2,
            blurRadius: 5,
            offset: Offset(0, 3),
          ),
        ],
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(
            playerName,
            style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
          ),
          Text(
            playerScore,
            style: TextStyle(fontSize: 16, color: Colors.green),
          ),
        ],
      ),
    );
  }
}