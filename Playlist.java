public class Playlist {
    private static class Node {
        Song song;
        Node next;

        Node(Song song) {
            this.song = song;
            this.next = null;
        }
    }

    private Node head;         // first song
    private Node tail;         // last song
    private Node currentNode;  // keeps track of currently playing song
    private int size;

    public Playlist() {
        this.head = null;
        this.tail = null;
        this.currentNode = null;
        this.size = 0;
    }

    // 1. Add Song
    public void addSong(Song song) {
        Node newNode = new Node(song);
        if (head == null) {  // playlist is empty
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode; // link old tail to new node
            tail = newNode;      // update tail
        }
        size++;
    }

    // 2. Remove Song by Title
    public void removeSong(String title) {
        if (head == null) return; // nothing to remove

        // Case 1: song is at the head
        if (head.song.getTitle().equalsIgnoreCase(title)) {
            head = head.next; // move head
            if (head == null) { // if list became empty
                tail = null;
            }
            size--;
            return;
        }

        // Case 2: song is in the middle or end
        Node prev = head;
        Node current = head.next;
        while (current != null) {
            if (current.song.getTitle().equalsIgnoreCase(title)) {
                prev.next = current.next;
                if (current == tail) { // if removed last node
                    tail = prev;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    // 3. Play Next Song
    public void playNext() {
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }

        if (currentNode == null) {
            currentNode = head; // start from the beginning
        } else {
            currentNode = currentNode.next;
            if (currentNode == null) { // wrapped around
                currentNode = head;
            }
        }

        System.out.println("Now playing: " + currentNode.song);
    }

    // 4. Display Playlist
    public void displayPlaylist() {
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }

        Node temp = head;
        System.out.println("Current Playlist:");
        while (temp != null) {
            System.out.println(" - " + temp.song);
            temp = temp.next;
        }
    }
}
