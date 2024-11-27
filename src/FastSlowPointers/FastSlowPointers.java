package FastSlowPointers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastSlowPointers {
    static class Node {
        int value = 0;
        Node next = null;

        Node(int value){
            this.value = value;
        }
    }
    static class LinkedList {
        private Node head;

        int size;
        LinkedList(){
            head = null;
            size = 0;
        }

        LinkedList(int value){
            head = null;
            size = 0;
            add(value);
        }

        void add(int value){
            Node node = new Node(value);
            if(size == 0){
                head = node;
            }else{
                Node temp = head;
                while(temp.next != null) temp = temp.next;
                temp.next = node;
            }
            size+=1;
        }

        void addCycle(int idx){
            Node temp = head;
            while (temp.next != null) temp = temp.next;

            Node cycleNode = head;
            while(idx != 1) {
                cycleNode = cycleNode.next;
                idx--;
            }
            temp.next = cycleNode;
        }

        Node getHead(){
            return head;
        }
    }

     static Node makeLinkedList(int[] arr, boolean hasCycle, int idx){
        LinkedList linkedList = new LinkedList();
        for (int el : arr) {
            linkedList.add(el);
        }
        if (hasCycle && linkedList.size > 1){
            if(idx > linkedList.size){
                throw new RuntimeException("Idx can not be greater than list size");
            }
            linkedList.addCycle(idx);
        }
        return linkedList.getHead();
    }


//    static void printList(Node head){
//        Node temp = head;
//        while(temp.next != null){
//            System.out.println(temp.value);
//            temp = temp.next;
//        };
//        System.out.println(temp.value);
//    }

    static boolean isLinkedListCyclic(Node head){
        Node fast = head;
        Node slow = head;

        while(fast != null && fast.next !=null){
            fast = fast.next.next;
            slow = slow.next;

            if(slow.value == fast.value){
                return true;
            }
        }
        return false;
    }

    static int lengthOfCycle(Node head){
        Node fast = head;
        Node slow = head;

        int length = 1;
        Node countCycle = null;
//        boolean hasCycle = false;
        while(fast != null && fast.next !=null){
            fast = fast.next.next;
            slow = slow.next;

            if(slow.value == fast.value){
                length += 1;
                countCycle = slow.next;
                break;
            }
        }
        while (countCycle.value != slow.value){
            length += 1;
            countCycle = countCycle.next;
        }
        return length;
    }

    //Problem Statement #
    //Given the head of a Singly LinkedList, write a method to return the middle node of the LinkedList.
    //
    //If the total number of nodes in the LinkedList is even, return the second middle node.
    //
    //Example 1:
    //
    //Input: 1 -> 2 -> 3 -> 4 -> 5 -> null
    //Output: 3
    //Example 2:
    //
    //Input: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
    //Output: 4
    //Example 3:
    //
    //Input: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> null
    //Output: 4

    static int findMiddleOfLinkedList(Node head){
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.value;
    }

    public static void main(String[] args) {
        System.out.println(findMiddleOfLinkedList(makeLinkedList(new int[]{1}, false, 0)));
    }
}
