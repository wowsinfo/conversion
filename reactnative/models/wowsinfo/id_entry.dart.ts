
interface IDEntry<T extends SearchResult> extends Encodable {
  entry: T;
  server: string;
}

class IDEntryImpl<T extends SearchResult> implements IDEntry<T> {
  entry: T;
  server: string;

  constructor(entry: T, server: string) {
    this.entry = entry;
    this.server = server;
  }

  encode(): string {
    // Implement the encoding logic here
    return JSON.stringify({
      entry: this.entry,
      server: this.server,
    });
  }
}

interface Entry {
  toJson: () => Record<string, any>;
}

class MyClass<T extends Entry> {
  entry: T;
  server: number; // get from game server

  constructor(entry: T, server: number) {
    this.entry = entry;
    this.server = server;
  }

  toJson(): Record<string, any> {
    return {
      entry: this.entry.toJson(),
      server: this.server,
    };
  }
}