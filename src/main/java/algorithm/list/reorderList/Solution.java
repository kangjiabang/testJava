package algorithm.list.reorderList;

import algorithm.list.ListNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author  kangjiabang
 * @CreateTime 2022-08-16  20:10
 * @Description TODO
 */
public class Solution {
    public void reorderList(ListNode head) {
        List<ListNode> list = new ArrayList<ListNode>();

        list.add(head);
        while (head.next != null) {
            head = head.next;
            list.add(head);
        }
        int i=0;
        int j=list.size() - 1;
        while (i<j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                list.get(j).next = null;
                break;}
            list.get(j).next = list.get(i);
            j--;
        }
    }

    @Test
    public void test() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;

        reorderList(listNode1);
        System.out.println(listNode1);
    }
}
