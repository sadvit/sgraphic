package com.sadvit.math;

import java.util.Collections;
import java.util.LinkedList;

public class DualList {

    private LinkedList<Node> clip;

    private LinkedList<Node> window;

    private LinkedList<Node> currentList;

    private int currentPosition;

    private Node start;

    private boolean first = true;

    public DualList(LinkedList<Node> clip, LinkedList<Node> window) {
        this.clip = clip;
        this.window = window;
        currentList = clip;
    }

    private void setStart(Node node, int i) {
        start = node;
        currentPosition = i;
    }

    private void shiftList(LinkedList<Node> nodes) {
        for (int i = 0; i < nodes.size(); i++) {
            Node prev = cyclicGet(nodes, i - 1);
            Node curr = cyclicGet(nodes, i);
            Node next = cyclicGet(nodes, i + 1);
            if (curr.isIntersect() && next.isInside() && !prev.isInside()) {
                Collections.rotate(nodes, nodes.size() - i + 1); // TODO check...
                break;
            }
        }
    }

    private Node cyclicGet(LinkedList<Node> nodes, int i) {
        if (i == -1) return nodes.get(nodes.size() - 2);
        if (i == nodes.size()) return nodes.get(0);
        return nodes.get(i);
    }

    public LinkedList<LinkedList<Node>> getSublists() {
        LinkedList<LinkedList<Node>> sublists = new LinkedList<>();
        shiftList(clip);
        for (int i = 0; i < clip.size() - 1; i++) { // TODO check "clip.size() - 1"
            Node node = clip.get(i);
            if (node.isIntersect()) {
                setStart(node, i);
                LinkedList<Node> sublist = getSublist();
                if (sublist.size() > 0) sublist.add(sublist.get(0));
                System.out.println("res: " + sublist);
                removeAll(sublist); // TODO check...
                sublists.add(sublist);
            }
        }
        return sublists;
    }

    private void removeAll(LinkedList<Node> nodes) {
        nodes.forEach(node -> {
            if (clip.contains(node))
                clip.remove(node);
            /*if (window.contains(node))
                window.remove(node);*/
        });
    }

    private LinkedList<Node> getSublist() {
        LinkedList<Node> sublist = new LinkedList<>();
        Node next;
        do {
            next = getNext();
            sublist.add(next);
        } while (!next.equals(start));
        return sublist;
    }

    private void changeList(Node node) {
        first = !first;
        currentList = first ? clip : window;
        currentPosition = currentList.indexOf(node);
    }

    private Node getCurrent() {
        if (currentPosition >= currentList.size()) {
            currentPosition = 0;
        }
        return currentList.get(currentPosition);
    }

    private Node getNext() {
        if (currentPosition < currentList.size()) {
            shift();
            Node current = getCurrent();
            if (current.isIntersect()) {
                changeList(current);
            }
            return current;
        } else {
            return null;
        }
    }

    private void shift() {
        currentPosition++;
    }

}
