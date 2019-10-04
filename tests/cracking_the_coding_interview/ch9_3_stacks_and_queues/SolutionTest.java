package cracking_the_coding_interview.ch9_3_stacks_and_queues;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 06-09-2019 21:27
 * Purpose: TODO:
 **/
class SolutionTest {
    @Test
    void testSortStack() {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        ArrayDeque<Integer> expected = new ArrayDeque<>();
        expected.push(3);
        expected.push(2);
        expected.push(1);
        Solution.sortStack(stack);
        assert Arrays.equals(stack.toArray(), expected.toArray());
    }

    @Test
    void testAnimalShelter() {
        Solution.AnimalShelter animalShelter = new Solution.AnimalShelter();
        animalShelter.enqueue(new Solution.Dog("dog1"));
        animalShelter.enqueue(new Solution.Cat("cat1"));
        animalShelter.enqueue(new Solution.Dog("dog2"));
        animalShelter.enqueue(new Solution.Dog("dog3"));
        animalShelter.enqueue(new Solution.Cat("cat2"));

        assert animalShelter.dequeueAny().equals(new Solution.Dog("dog1"));
        assert animalShelter.dequeueDog().equals(new Solution.Dog("dog2"));
        assert animalShelter.dequeueAny().equals(new Solution.Cat("cat1"));
        assert animalShelter.dequeueCat().equals(new Solution.Cat("cat2"));
    }

    @Test
    void testAnimalShelter2() {
        Solution.AnimalShelter2 animalShelter = new Solution.AnimalShelter2();
        animalShelter.enqueue(new Solution.Dog("dog1"));
        animalShelter.enqueue(new Solution.Cat("cat1"));
        animalShelter.enqueue(new Solution.Dog("dog2"));
        animalShelter.enqueue(new Solution.Dog("dog3"));
        animalShelter.enqueue(new Solution.Cat("cat2"));

        assert animalShelter.dequeueAny().equals(new Solution.Dog("dog1"));
        assert animalShelter.dequeueDog().equals(new Solution.Dog("dog2"));
        assert animalShelter.dequeueAny().equals(new Solution.Cat("cat1"));
        assert animalShelter.dequeueCat().equals(new Solution.Cat("cat2"));
    }
}
