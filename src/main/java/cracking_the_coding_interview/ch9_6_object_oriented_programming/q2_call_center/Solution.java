package cracking_the_coding_interview.ch9_6_object_oriented_programming.q2_call_center;

import java.util.ArrayDeque;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-09-2019 19:10
 * Purpose: TODO:
 **/
class CallCenter {
    ArrayDeque<Employee> respondents = new ArrayDeque<>();

    void dispatchCall(Call call) {
        while (!respondents.isEmpty()) {
            Employee respondent = respondents.peek();
            if (respondent == null) {
                throw new RuntimeException("No respondent available");
            }
            if (respondent.isAvailable) {
                respondents.poll();
                respondent.isAvailable = false;
                respondent.assignCall(call);
                respondent.isAvailable = true;
                respondents.add(respondent);
                break;
            }
        }
    }
}

class Call {
    User user;
}

class User {

}

abstract class Employee {
    String name;
    boolean isAvailable;
    Employee manager;

    abstract void assignCall(Call call);

    abstract boolean handle(Call call);

    void escalate(Call call) {
        if (manager == null) {
            throw new RuntimeException("Can't escalate the call");
        }
        manager.assignCall(call);
    }
}

class Respondent extends Employee {
    @Override
    void assignCall(Call call) {
        if (!handle(call)) {
            escalate(call);
        }
    }

    @Override
    boolean handle(Call call) {
        return false;
    }
}

class Manager extends Employee {
    @Override
    void assignCall(Call call) {
        if (!handle(call)) {
            escalate(call);
        }
    }

    @Override
    boolean handle(Call call) {
        return false;
    }
}

class Director extends Employee {

    @Override
    void assignCall(Call call) {
        if (!handle(call)) {
            throw new RuntimeException("Director couldn't solve the problem it is an issue.");
        }
    }

    @Override
    boolean handle(Call call) {
        return false;
    }

    @Override
    void escalate(Call call) {
        throw new RuntimeException("Can't escalate a director's call");
    }
}
