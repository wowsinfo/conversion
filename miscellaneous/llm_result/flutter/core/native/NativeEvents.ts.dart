
class NativeEvents {
  static const EventChannel eventChannel = EventChannel('com.example.native_events');

  NativeEvents() {
    eventChannel.receiveBroadcastStream().listen((dynamic event) {
      if (event['type'] == 'dummy') {
        dataLoading();
        print('dummy event received from native: ${event['data']}');
      }
    });
  }

  void dataLoading() {
    // Implement your data loading logic here
  }
}

  // Your data loading logic here
}