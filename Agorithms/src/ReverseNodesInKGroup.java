

public class ReverseNodesInKGroup {

	public static void main(String[] args) {
		//Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
		//k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
		//Example:
		//Given this linked list: 1->2->3->4->5
		//For k = 2, you should return: 2->1->4->3->5
		//For k = 3, you should return: 3->2->1->4->5
		//Note:
		//Only constant extra memory is allowed.
		//You may not alter the values in the list's nodes, only nodes itself may be changed.
		
		
	}
	
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        int counter = 0;
        while (counter < k && cur != null) {
            cur = cur.next;
            counter++;
        }
        if (counter == k) {
            ListNode subHead = reverseKGroup(cur, k);
            cur = head; 
            for (int i = 0; i < k; i++) {
                ListNode next = cur.next;
                cur.next = subHead;
                subHead = cur;
                cur = next; 
            }
            return subHead; 
        }
        return head;
    }
    
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
