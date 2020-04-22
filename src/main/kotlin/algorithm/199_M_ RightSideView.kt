package algorithm

/**
给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

示例:

输入: [1,2,3,null,5,null,4]
输出: [1, 3, 4]
解释:

1            <---
/   \
2     3         <---
\     \
5     4       <---

通过广度优先算法，进行遍历

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun rightSideView(root: TreeNode?): List<Int> {
    if (root == null) {
        return mutableListOf()
    }

    val list = mutableListOf<Int>()
    val layer = mutableListOf<TreeNode>()
    layer.add(root)
    while(layer.isNotEmpty()) {
        val temp = mutableListOf<TreeNode>()

        for (node in layer) {
            val left = node.left
            if (left != null) {
                temp.add(left)
            }

            val right = node.right
            if (right != null) {
                temp.add(right)
            }
        }

        list.add(layer[layer.size - 1].`val`)

        layer.clear()
        layer.addAll(temp)
    }
    return list
}
