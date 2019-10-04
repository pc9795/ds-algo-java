package cracking_the_coding_interview.ch9_3_stacks_and_queues;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 05-09-2019 23:13
 * Purpose: TODO:
 **/
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
            if (isEmpty()) {
                throw new RuntimeException("Empty stack");
            }
            int poppedItem = stacks.get(currStack).pop();
            if (stacks.get(currStack).isEmpty()) {
                stacks.remove(currStack);
                currStack--;
            }
            return poppedItem;
        }

//        We can also shift the stacks to maintain the threshold.
        public int popAt(int index) {
            if (index >= stacks.size() || index < 0) {
                throw new RuntimeException("Invalid index");
            }
            int poppedItem = stacks.get(index).pop();
            if (stacks.get(index).isEmpty()) {
                stacks.remove(index);
                currStack--;
            }
            return poppedItem;
        }

        public int peek() {
            if (isEmpty()) {
                throw new RuntimeException("Empty stack");
            }
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
            if (isEmpty()) {
                throw new RuntimeException("Empty Stack");
            }
            return main.peek();
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("Empty Stack");
            }
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

    static void sortStack(ArrayDeque<Integer> stack) {
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

    static abstract class Animal {
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

    static class Dog extends Animal {

        public Dog(String name) {
            super(name);
        }
    }

    static class Cat extends Animal {

        public Cat(String name) {
            super(name);
        }
    }

    static class AnimalShelter {
        LinkedList<Animal> data = new LinkedList<>();

        boolean isEmpty() {
            return data.isEmpty();
        }

        void enqueue(Animal item) {
            data.add(item);
        }

        Animal dequeueAny() {
            if (isEmpty()) {
                throw new RuntimeException("Stack empty");
            }
            Animal poppedItem = data.get(0);
            data.remove(0);
            return poppedItem;
        }

        Animal dequeueAnimal(Class<? extends Animal> clazz) {
            if (isEmpty()) {
                throw new RuntimeException("Stack empty");
            }
            int i = 0;
            for (; i < data.size(); i++) {
                if (data.get(i).getClass() == clazz) {
                    break;
                }
            }
            if (i == data.size()) {
                throw new RuntimeException("Particular animal: " + clazz + " not found");
            }
            Animal poppedItem = data.get(i);
            data.remove(i);
            return poppedItem;
        }

        Animal dequeueCat() {
            return dequeueAnimal(Cat.class);
        }

        Animal dequeueDog() {
            return dequeueAnimal(Dog.class);
        }
    }

    static class AnimalShelter2 {

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

        void enqueue(Animal item) {
            item.order = order;
            order++;
            if (item instanceof Dog) {
                dogsList.add((Dog) item);
            } else if (item instanceof Cat) {
                catList.add((Cat) item);
            }
        }

        Animal dequeueAny() {
            if (isEmpty(Dog.class) && isEmpty(Cat.class)) {
                throw new RuntimeException("Stack empty");
            }
            Animal poppedAnimal = null;
            if (dogsList.get(0).order < catList.get(0).order) {
                poppedAnimal = dogsList.get(0);
                dogsList.remove(0);
            } else {
                poppedAnimal = catList.get(0);
                catList.remove(0);
            }
            return poppedAnimal;
        }

        Animal dequeueCat() {
            if (isEmpty(Cat.class)) {
                throw new RuntimeException("Stack empty");
            }
            Animal poppedItem = catList.get(0);
            catList.remove(0);
            return poppedItem;
        }

        Animal dequeueDog() {
            if (isEmpty(Dog.class)) {
                throw new RuntimeException("Stack empty");
            }
            Animal poppedItem = dogsList.get(0);
            dogsList.remove(0);
            return poppedItem;
        }
    }
}
