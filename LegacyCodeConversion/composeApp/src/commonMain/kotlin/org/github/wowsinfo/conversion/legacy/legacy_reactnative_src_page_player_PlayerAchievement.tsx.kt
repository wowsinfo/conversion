
export const PlayerAchievement = ({data}: any) => {
  const [displayData, setDisplayData] = useState<Array<any>>([])

  useEffect(() => {
    let saved = AppGlobalData.get(SAVED.achievement)

    val formatted = mutableMapOf<String, Any>()
    for (key in data.keys) {
      val obj = saved[key]
      if (obj != null) {
        formatted.put(key, obj)
      }
    }

    formatted.toSorted { a, b -> b.value - a.value }.forEach {
      setDisplayData(it.value as Array<Any>)
    }
  }, [data])

  return (
    <WoWsInfo title={`${lang.tab_achievement_title} - ${displayData.size}`}>
      <Anime.View useNativeDriver animation="fadeIn">
        <FlatGrid
          itemDimension={80}
          data={displayData}
          renderItem={({item}) => {
            return (
              <Touchable
                onPress={() => SafeAction('BasicDetail', {item: item.data})}>
                <WikiIcon item={item.data} />
                <Paragraph style={styles.number}>{item.num}</Paragraph>
              </Touchable>
            )
          }}
          showsVerticalScrollIndicator={false}
        />
      </Anime.View>
    </WoWsInfo>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center'
  },
  number: {
    textAlign: 'center'
  }
})