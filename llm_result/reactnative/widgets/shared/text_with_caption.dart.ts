
interface TextWithCaptionProps {
  title?: string;
  value?: string;
  titleWidget?: React.ReactNode;
  valueWidget?: React.ReactNode;
}

const TextWithCaption: React.FC<TextWithCaptionProps> = ({
  title = '',
  value = '',
  titleWidget,
  valueWidget,
}) => {
  return (
    <View style={styles.container}>
      {titleWidget ? titleWidget : <Text style={styles.title}>{title}</Text>}
      {valueWidget ? valueWidget : <Text style={styles.value}>{value}</Text>}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    alignItems: 'center',
  },
  title: {
    fontSize: 16,
    fontWeight: 'bold',
  },
  value: {
    fontSize: 14,
  },
});

export default TextWithCaption;


interface Props {
  title: string;
  titleWidget?: React.ReactNode;
  value: string;
  valueWidget?: React.ReactNode;
}

const MyComponent: React.FC<Props> = ({ title, titleWidget, value, valueWidget }) => {
  return (
    <View style={styles.container}>
      {buildTitle()}
      {buildValue()}
    </View>
  );

  function buildTitle() {
    return titleWidget ? (
      titleWidget
    ) : (
      <Text style={styles.title}>{title}</Text>
    );
  }

  function buildValue() {
    return valueWidget ? (
      valueWidget
    ) : (
      <Text style={styles.value}>{value}</Text>
    );
  }
};

const styles = StyleSheet.create({
  container: {
    paddingTop: 4,
  },
  title: {
    fontSize: 16,
    fontWeight: 'bold',
  },
  value: {
    fontSize: 14,
  },
});

export default MyComponent;


interface Props {
  value: string;
  valueWidget?: JSX.Element;
}

const ValueComponent: React.FC<Props> = ({ value, valueWidget }) => {
  const buildValue = () => {
    if (valueWidget) return valueWidget;
    return (
      <Text style={{ textAlign: 'center', numberOfLines: 2 }}>
        {value}
      </Text>
    );
  };

  return <View>{buildValue()}</View>;
};

export default ValueComponent;


interface Props {
  title: string;
  titleWidget?: React.ReactNode;
}

const TitleComponent: React.FC<Props> = ({ title, titleWidget }) => {
  return (
    <View style={styles.container}>
      {titleWidget ? (
        titleWidget
      ) : (
        <Text style={styles.title} numberOfLines={2}>
          {title}
        </Text>
      )}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    alignItems: 'center',
  },
  title: {
    textAlign: 'center',
    fontSize: 12, // Adjust according to your theme
    color: '#000', // Adjust according to your theme
  },
});

export default TitleComponent;


const App: React.FC = () => {
  const [count, setCount] = React.useState(0);

  const increment = () => {
    setCount(count + 1);
  };

  const decrement = () => {
    setCount(count - 1);
  };

  return (
    <View style={styles.container}>
      <Text style={styles.text}>Count: {count}</Text>
      <View style={styles.buttonContainer}>
        <Button title="Increment" onPress={increment} />
        <Button title="Decrement" onPress={decrement} />
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  text: {
    fontSize: 24,
    marginBottom: 20,
  },
  buttonContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    width: '60%',
  },
});

export default App;