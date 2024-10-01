
export interface StoreInterface {
  load(): Promise<boolean>;
  remove(key: string): Promise<boolean>;
  clear(): Promise<boolean>;
  has(key: string): boolean;
  get(key: string): Promise<Object | null>;
  set(key: string, value: Object): Promise<boolean>;
}

export class Store implements StoreInterface {
  async load(): Promise<boolean> {
    // Load logic can be implemented if needed
    return true;
  }

  async remove(key: string): Promise<boolean> {
    try {
      await AsyncStorage.removeItem(key);
      return true;
    } catch (error) {
      return false;
    }
  }

  async clear(): Promise<boolean> {
    try {
      await AsyncStorage.clear();
      return true;
    } catch (error) {
      return false;
    }
  }

  has(key: string): boolean {
    return this.get(key) !== null;
  }

  async get(key: string): Promise<Object | null> {
    try {
      const value = await AsyncStorage.getItem(key);
      return value !== null ? JSON.parse(value) : null;
    } catch (error) {
      return null;
    }
  }

  async set(key: string, value: Object): Promise<boolean> {
    try {
      await AsyncStorage.setItem(key, JSON.stringify(value));
      return true;
    } catch (error) {
      return false;
    }
  }
}