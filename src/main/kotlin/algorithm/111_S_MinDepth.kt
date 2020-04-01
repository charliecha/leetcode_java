package algorithm

/**
给定一个二叉树，找出其最小深度。

最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

说明: 叶子节点是指没有子节点的节点。

示例:

给定二叉树 [3,9,20,null,null,15,7],

3
/ \
9  20
/  \
15   7
返回它的最小深度  2.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun minDepth(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }

    return minDepth(root, 0)
}

fun minDepth(root: TreeNode, depth: Int): Int {
    if (isLeaf(root)) {
        return depth + 1
    }

    if (root.right == null) {
        return minDepth(root.left!!, depth + 1)
    }

    if (root.left == null) {
        return minDepth(root.right!!, depth + 1)
    }

    return kotlin.math.min(
        minDepth(root.left!!, depth + 1),
        minDepth(root.right!!, depth + 1)
    )
}

fun isLeaf(root: TreeNode): Boolean {
    return root.left == null && root.right == null
}

fun main() {

}