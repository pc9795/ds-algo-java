package ctci.ch9_3_stacks_and_queues;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

public class Solution {

  static class SetOfStacks {
    int currStack;
    List<ArrayDeque<Integer>> stacks;
    int threshold;

    public SetOfStacks(int threshold) {
      currStack = -1;
    }

    public boolean isEmpty() {
      return currStack == -1;
    }

    public void push(int item) {
      if (currStack == -1) {
        currStack = 0;
        stacks.add(new ArrayDeque<>());
      }
      if (stacks.get(currStack).size() == threshold) {
        currStack++;
        stacks.add(new ArrayDeque<>());
      }
      stacks.get(currStack).push(item);
    }

    public int pop() {
      assert !isEmpty() : "Empty stack";

      int poppedItem = stacks.get(currStack).pop();
      if (stacks.get(currStack).isEmpty()) {
        stacks.remove(currStack);
        currStack--;
      }
      return poppedItem;
    }

    //        We can also shift the stacks to maintain the threshold.
    public int popAt(int index) {
      assert index < stacks.size() && index >= 0 : "Invalid index";

      int poppedItem = stacks.get(index).pop();
      if (stacks.get(index).isEmpty()) {
        stacks.remove(index);
        currStack--;
      }
      return poppedItem;
    }

    public int peek() {
      assert !isEmpty() : "Empty stack";
      assert !stacks.get(currStack).isEmpty();

      return stacks.get(currStack).peek();
    }
  }

  static class SortStock {
    ArrayDeque<Integer> main;
    ArrayDeque<Integer> temp;

    public SortStock() {
      main = new ArrayDeque<>();
      temp = new ArrayDeque<>();
    }

    public boolean isEmpty() {
      return main.isEmpty();
    }

    public int peek() {
      assert !isEmpty() : "Empty stack";

      return main.peek();
    }

    public int pop() {
      assert !isEmpty() : "Empty stack";

      return main.pop();
    }

    public void push(int item) {
      if (isEmpty()) {
        main.push(item);
        return;
      }
      while (!main.isEmpty() && main.peek() <= item) {
        temp.push(main.pop());
      }

      main.push(item);

      while (!temp.isEmpty()) {
        main.push(temp.pop());
      }
    }
  }

  public static void sortStack(ArrayDeque<Integer> stack) {
    ArrayDeque<Integer> tempStack = new ArrayDeque<>();
    while (!stack.isEmpty()) {
      int temp = stack.pop();
      while (!tempStack.isEmpty() && tempStack.peek() > temp) {
        stack.push(tempStack.pop());
      }
      tempStack.push(temp);
    }

    while (!tempStack.isEmpty()) {
      stack.push(tempStack.pop());
    }
  }

  public abstract static class Animal {
    int order;
    String name;

    Animal(String name) {
      this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof Animal)) {
        return false;
      }
      return ((Animal) obj).name.equals(this.name);
    }
  }

  public static class Dog extends Animal {

    public Dog(String name) {
      super(name);
    }
  }

  public static class Cat extends Animal {

    public Cat(String name) {
      super(name);
    }
  }

  public static class AnimalShelter {
    LinkedList<Animal> data = new LinkedList<>();

    boolean isEmpty() {
      return data.isEmpty();
    }

    public void enqueue(Animal item) {
      data.add(item);
    }

    public Animal dequeueAny() {
      assert !isEmpty() : "Empty stack";

      Animal poppedItem = data.get(0);
      data.remove(0);
      return poppedItem;
    }

    Animal dequeueAnimal(Class<? extends Animal> clazz) {
      assert !isEmpty() : "Empty stack";

      int i = 0;
      for (; i < data.size(); i++) {
        if (data.get(i).getClass() == clazz) {
          break;
        }
      }
      assert i != data.size() : "Particular animal: " + clazz + " not found";

      Animal poppedItem = data.get(i);
      data.remove(i);
      return poppedItem;
    }

    public Animal dequeueCat() {
      return dequeueAnimal(Cat.class);
    }

    public Animal dequeueDog() {
      return dequeueAnimal(Dog.class);
    }
  }

  public static class AnimalShelter2 {

    LinkedList<Dog> dogsList = new LinkedList<>();
    LinkedList<Cat> catList = new LinkedList<>();
    int order = 0;

    boolean isEmpty(Class<? extends Animal> clazz) {
      if (clazz == Dog.class) {
        return dogsList.isEmpty();
      } else if (clazz == Cat.class) {
        return catList.isEmpty();
      }
      throw new RuntimeException("Unrecognized input");
    }

    public void enqueue(Animal item) {
      item.order = order;
      order++;
      if (item instanceof Dog) {
        dogsList.add((Dog) item);
      } else if (item instanceof Cat) {
        catList.add((Cat) item);
      }
    }

    public Animal dequeueAny() {
      assert !isEmpty(Dog.class) || !isEmpty(Cat.class) : "Empty stack";

      Animal poppedAnimal;
      if (dogsList.get(0).order < catList.get(0).order) {
        poppedAnimal = dogsList.get(0);
        dogsList.remove(0);
      } else {
        poppedAnimal = catList.get(0);
        catList.remove(0);
      }
      return poppedAnimal;
    }

    public Animal dequeueCat() {
      assert !isEmpty(Cat.class) : "Empty stack";

      Animal poppedItem = catList.get(0);
      catList.remove(0);
      return poppedItem;
    }

    public Animal dequeueDog() {
      assert !isEmpty(Dog.class) : "Empty stack";

      Animal poppedItem = dogsList.get(0);
      dogsList.remove(0);
      return poppedItem;
    }
  }
}
