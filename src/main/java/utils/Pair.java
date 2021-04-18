package utils;

import java.util.Objects;

public class Pair<K, V> {
  public K key;
  public V value;

  public Pair(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public K getKey() {
    return key;
  }

  public V getValue() {
    return value;
  }

  public static <K, V> Pair<K, V> of(K key, V value) {
    return new Pair<>(key, value);
  }

  @Override
  public String toString() {
    return key + "=" + value;
  }

  @Override
  public int hashCode() {
    return key.hashCode() * 13 + (value == null ? 0 : value.hashCode());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o instanceof Pair) {
      Pair pair = (Pair) o;
      if (!Objects.equals(key, pair.key)) return false;
      return Objects.equals(value, pair.value);
    }
    return false;
  }
}
