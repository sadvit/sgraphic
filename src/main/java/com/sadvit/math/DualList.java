package com.sadvit.math;

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

    private void setStart(Node node) {
        start = node;
        shift();
    }

    public LinkedList<LinkedList<Node>> getSublists() {
        LinkedList<LinkedList<Node>> sublists = new LinkedList<>();
        for (int i = 0; i < clip.size(); i++) {
            Node node = clip.get(i);
            if (node.isIntersect()) {
                setStart(node);
                LinkedList<Node> sublist = getSublist();
                sublists.add(sublist);
                removeAll(sublist);
            }
        }
        return sublists;
    }

    private void removeAll(LinkedList<Node> nodes) {
        nodes.forEach(node -> {
            if (clip.contains(node))
                clip.remove(node);
            if (window.contains(node))
                window.remove(node);
        });
    }

    private LinkedList<Node> getSublist() {
        LinkedList<Node> sublist = new LinkedList<>();
        Node next = getCurrent();
        do {
            sublist.add(next);
            next = getNext();
        } while (!next.equals(start));
        return sublist;
    }

    private void changeList(Node node) {
        first = !first;
        currentList = first ? clip : window;
        currentPosition = currentList.indexOf(node);
    }

    private Node getCurrent() {
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
