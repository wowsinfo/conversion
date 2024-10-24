
interface ChartValue<T, V> {
  name: T;
  value: V;
}

class ChartUtils {
  static colours: string[] = [
    '#2196F3', // blue
    '#F44336', // red
    '#FFEB3B', // yellow
    '#4CAF50', // green
    '#9C27B0', // purple
    '#00BCD4', // cyan
    '#FF5722', // deepOrange
    '#3F51B5', // indigo
    '#CDDC39', // lime
    '#E91E63', // pink
    '#009688', // teal
    '#9E9E9E', // gray
  ];

  static from(index: number): string {
    return this.colours[index % this.colours.length];
  }

  static damageColour: string = '#D32F2F';
  static fragsColour: string = '#2196F3';
  static winrateColour: string = '#4CAF50';
  static battleColour: string = '#FF9800';

  static convert<T, V>(
    id: string,
    values: ChartValue<T, V>[],
    color: string,
    domainFn: (value: ChartValue<T, V>, index?: number) => T,
    measureFn: (value: ChartValue<T, V>, index?: number) => number | null,
    labelFormatter?: (value: ChartValue<T, V>, index?: number) => string
  ) {
    return {
      id,
      data: values.map((value, index) => ({
        x: domainFn(value, index),
        y: measureFn(value, index),
        label: labelFormatter ? labelFormatter(value, index) : undefined,
      })),
      color,
    };
  }
}

const ChartComponent: React.FC<{ data: ChartValue<string, number>[] }> = ({ data }) => {
  const series = ChartUtils.convert(
    'example',
    data,
    ChartUtils.from(0),
    (value) => value.name,
    (value) => value.value,
    (value) => `${value.name}: ${value.value}`
  );

  return (
    <View>
      <VictoryChart theme={VictoryTheme.material}>
        <VictoryBar
          data={series.data}
          style={{ data: { fill: series.color } }}
          labels={({ datum }) => datum.label}
        />
      </VictoryChart>
    </View>
  );
};

export default ChartComponent;


interface SeriesProps {
  id: string;
  values: number[];
  domainFn: (value: number) => number;
  measureFn: (value: number) => number;
  labelFormatter: (value: number) => string;
  color: string;
}

const Series: React.FC<SeriesProps> = ({ id, values, domainFn, measureFn, labelFormatter, color }) => {
  return (
    <VictoryChart>
      <VictoryBar
        data={values.map((value, index) => ({
          x: domainFn(value),
          y: measureFn(value),
          label: labelFormatter(value),
        }))}
        style={{ data: { fill: color } }}
      />
    </VictoryChart>
  );
};

export default Series;


interface ConvertDefaultProps<T> {
    id: string;
    values: ChartValue<T, number>[];
    color: Color;
    labelFormatter?: (value: ChartValue<T, number>, index: number | null) => string;
}

function convertDefault<T>({ id, values, color, labelFormatter }: ConvertDefaultProps<T>): Series<ChartValue<T, number>, T> {
    // Implement the conversion logic here
    const seriesData = values.map((value, index) => ({
        ...value,
        label: labelFormatter ? labelFormatter(value, index) : value.label,
    }));

    return new Series<ChartValue<T, number>, T>(id, seriesData, color);
}

const ChartComponent = <T,>({ id, values, color, labelFormatter }: ConvertDefaultProps<T>) => {
    const series = convertDefault({ id, values, color, labelFormatter });

    return (
        <View>
            {/* Render your chart using the series data */}
        </View>
    );
};

export default ChartComponent;


interface DataPoint {
  name: string;
  value: number;
}

interface Props {
  id: string;
  values: DataPoint[];
  color: string;
  labelFormatter?: (value: string) => string;
}

const ChartComponent: React.FC<Props> = ({ id, values, color, labelFormatter }) => {
  return (
    <View>
      <VictoryChart>
        <VictoryBar
          data={values}
          x="name"
          y="value"
          style={{ data: { fill: color } }}
          labels={({ datum }) => labelFormatter ? labelFormatter(datum.name) : datum.name}
          labelComponent={<VictoryLabel dy={-10} />}
        />
      </VictoryChart>
    </View>
  );
};

export default ChartComponent;


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
    backgroundColor: '#F5FCFF',
  },
  text: {
    fontSize: 20,
    margin: 10,
  },
  buttonContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    width: '60%',
  },
});

export default App;