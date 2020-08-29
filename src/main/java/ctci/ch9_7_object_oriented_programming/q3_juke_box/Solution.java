package ctci.ch9_7_object_oriented_programming.q3_juke_box;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Person {
  String name;

  String decideSelection(Set<String> selectionNames) {
    return null;
  }
}

class JukeBox {
  Map<String, Button> nameToButtons = new HashMap<>();

  Set<String> getSelectionNames() {
    return nameToButtons.keySet();
  }

  void playSelection(String selectionName) {
    if (!nameToButtons.containsKey(selectionName)) {
      throw new RuntimeException("Selection doesn't exist");
    }
    nameToButtons.get(selectionName).play();
  }
}

class Button {
  String name;
  Album album;

  void play() {
    album.play();
  }
}

class Album {
  Record[] records;

  void play() {
    for (Record record : records) {
      record.play();
    }
  }
}

class Record {
  String artist;
  String song;

  void play() {}
}
