/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */

 func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
    newList := &ListNode{} // 구조체의 새 인스턴스를 생성하고 포인터 반환

    current := newList
    carry := 0

    for l1 != nil || l2 != nil || carry != 0 {
        sum := carry

        if l1 != nil {
            sum += l1.Val
            l1 = l1.Next
        }

        if l2 != nil {
            sum += l2.Val
            l2 = l2.Next
        }

        carry = sum / 10
        current.Next = &ListNode{Val: sum % 10} // 새로운 ListNode를 생성하여 리스트에 연결
        current = current.Next
    }

    return newList.Next
}