/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
 
class Solution {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        
        val newList = ListNode(0)
        var current = newList
        var carry = 0

        var p1 = l1
        var p2 = l2

        while (p1 != null || p2 != null || carry != 0) {
            val sum = (p1?.`val` ?: 0) + (p2?.`val` ?: 0) + carry
            carry = sum / 10
            current.next = ListNode(sum % 10)
            current = current.next

            p1 = p1?.next
            p2 = p2?.next
        }

        return newList.next
    }
}