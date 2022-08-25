package algorithm.list.mergeList;

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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode head = new ListNode(-1);
        ListNode pre = head;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                pre.next = list1;
                list1 = list1.next;
            } else {
                pre.next = list2;
                list2 = list2.next;
            }
            pre = pre.next;
        }
        if (list1 == null) {
            pre.next = list2;
        } else {
            pre.next = list1;
        }
        return head.next;

    }

    @Test
    public void test() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode1_next = new ListNode(2);
        ListNode listNode2 = new ListNode(1);
        ListNode listNode2_next = new ListNode(3);
        listNode1.next = listNode1_next;
        listNode2.next = listNode2_next;

        ListNode result = mergeTwoLists(listNode1,listNode2);
        System.out.println(result);
    }
}
