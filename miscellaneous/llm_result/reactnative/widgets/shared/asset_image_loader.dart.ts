
interface AssetImageLoaderProps {
  name: string;
  placeholder?: any;
}

const AssetImageLoader: React.FC<AssetImageLoaderProps> = ({ name, placeholder }) => {
  const [loading, setLoading] = React.useState(true);
  const [error, setError] = React.useState(false);

  const handleLoad = () => {
    setLoading(false);
  };

  const handleError = () => {
    setLoading(false);
    setError(true);
  };

  return (
    <View style={styles.container}>
      {loading && !error && (
        <ActivityIndicator size="small" color="#0000ff" />
      )}
      {!loading && !error && (
        <Image
          source={{ uri: name }}
          style={styles.image}
          onLoad={handleLoad}
          onError={handleError}
        />
      )}
      {error && placeholder && (
        <Image source={placeholder} style={styles.image} />
      )}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    justifyContent: 'center',
    alignItems: 'center',
    width: '100%',
    height: '100%',
  },
  image: {
    width: '100%',
    height: '100%',
    resizeMode: 'cover',
  },
});

export default AssetImageLoader;


interface Props {
  name: string;
  placeholder?: string;
}

const CustomImage: React.FC<Props> = ({ name, placeholder }) => {
  const [error, setError] = React.useState(false);

  return (
    <View>
      {error ? (
        placeholder ? (
          <Image
            source={{ uri: placeholder }}
            style={{ width: '100%', height: '100%' }}
            onError={() => setError(true)}
          />
        ) : (
          <View style={{ justifyContent: 'center', alignItems: 'center', width: '100%', height: '100%' }}>
            <ActivityIndicator size="large" color="red" />
          </View>
        )
      ) : (
        <Image
          source={{ uri: name }}
          style={{ width: '100%', height: '100%' }}
          onError={() => setError(true)}
        />
      )}
    </View>
  );
};

export default CustomImage;


interface AssetImageLoaderProps {
  name: string;
  placeholder?: string;
}

const AssetImageLoader: React.FC<AssetImageLoaderProps> = ({ name, placeholder }) => {
  const logger = new Logger('AssetImageLoader');
  const [image, setImage] = useState<JSX.Element | null>(null);

  useEffect(() => {
    const loadImage = async () => {
      try {
        const response = await fetch(`path/to/assets/${name}`); // Adjust the path as necessary
        const blob = await response.blob();
        const imageUrl = URL.createObjectURL(blob);
        setImage(<Image source={{ uri: imageUrl }} style={{ width: '100%', height: '100%' }} />);
      } catch (e) {
        logger.warning(`Failed to load image: ${name}`);
        if (placeholder) {
          setImage(<Image source={{ uri: placeholder }} style={{ width: '100%', height: '100%' }} />);
        } else {
          setImage(<View style={{ width: '100%', height: '100%', justifyContent: 'center', alignItems: 'center' }}><Icon name="close" color="red" /></View>);
        }
      }
    };

    loadImage();
  }, [name, placeholder]);

  return (
    <View style={{ width: '100%', height: '100%' }}>
      {image ? image : <ActivityIndicator size="large" color="#0000ff" />}
    </View>
  );
};

export default AssetImageLoader;