package algorithm

/**
有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？

如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。

你允许：

装满任意一个水壶
清空任意一个水壶
从一个水壶向另外一个水壶倒水，直到装满或者倒空
示例 1: (From the famous "Die Hard" example)

输入: x = 3, y = 5, z = 4
输出: True
示例 2:

输入: x = 2, y = 6, z = 5
输出: False

 * 问题转换成树形搜索问题，从一个节点查找，没找到就查找它分裂的子节点。
 * 此处使用广度查找算法
 * 需要记录历史记录，避免陷入死循环
 */
fun canMeasureWater(x: Int, y: Int, z: Int): Boolean {
    val list = mutableListOf<KettleNode>()
    val history = mutableSetOf<KettleNode>()
    list.add(KettleNode(KettleState(Kettle(x), 0), KettleState(Kettle(y), 0)))
    while (list.isNotEmpty()) {
        val node = list.removeAt(0)

        if (history.contains(node)) {
            continue
        }

        history.add(node)

        if (match(node, z)) {
            return true
        }
        expand(node, list, history)
    }
    return false
}

/**
 * 扩张node成为多个节点
 */
fun expand(current: KettleNode, toSearch: MutableList<KettleNode>, history: MutableSet<KettleNode>) {
    val state1 = current.state1
    val state2 = current.state2

    // 填充 或者 清空, 壶之间没有关联
    val list1 = mutableListOf<KettleState>()
    if (!state1.isFill()) {
        list1.add(KettleState(state1.kettle, state1.capacity))
    }
    if (!state1.isEmpty()) {
        list1.add(KettleState(state1.kettle, 0))
    }
    val list2 = mutableListOf<KettleState>()
    if (!state2.isFill()) {
        list2.add(KettleState(state2.kettle, state2.capacity))
    }
    if (!state2.isEmpty()) {
        list2.add(KettleState(state2.kettle, 0))
    }

    val nodes = mutableListOf<KettleNode>()
    for (i in 0 until list1.size) {
        nodes.add(KettleNode(list1[i], state2))
    }

    for (j in 0 until list2.size) {
        nodes.add(KettleNode(state1, list2[j]))
    }

    for (i in 0 until list1.size) {
        for (j in 0 until list2.size) {
            nodes.add(KettleNode(list1[i], list2[j]))
        }
    }

    // 转移，壶之间有关联
    // 壶1 -> 壶2
    if (!state1.isEmpty() && !state2.isFill()) {
        val node = if (state1.fill + state2.fill > state2.capacity) {
            KettleNode(
                KettleState(state1.kettle, state1.fill + state2.fill - state2.capacity),
                KettleState(state2.kettle, state2.capacity)
            )
        } else {
            KettleNode(
                KettleState(state1.kettle, 0),
                KettleState(state2.kettle, state1.fill + state2.fill)
            )
        }
        nodes.add(node)
    }

    // 壶2 -> 壶1
    if (!state2.isEmpty() && !state1.isFill()) {
        val node = if (state1.fill + state2.fill <= state1.capacity) {
            KettleNode(
                KettleState(state1.kettle, state1.fill + state2.fill),
                KettleState(state2.kettle, 0)
            )
        } else {
            KettleNode(
                KettleState(state1.kettle, state1.capacity),
                KettleState(state2.kettle, state1.fill + state2.fill - state2.capacity)
            )
        }
        nodes.add(node)
    }

    for (node in nodes) {
        if (history.contains(node)) {
            continue
        }

        toSearch.add(node)
    }
}

fun match(node: KettleNode, target: Int): Boolean {
    return node.state1.fill == target
            || node.state2.fill == target
            || node.state1.fill + node.state2.fill == target
}

class Kettle(val capacity: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Kettle) return false

        if (capacity != other.capacity) return false

        return true
    }

    override fun hashCode(): Int {
        return capacity
    }
}

class KettleState(val kettle: Kettle, val fill: Int) {
    val capacity: Int
        get() = kettle.capacity

    fun isEmpty(): Boolean {
        return fill == 0
    }

    fun isFill(): Boolean {
        return kettle.capacity == fill
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is KettleState) return false

        if (kettle != other.kettle) return false
        if (fill != other.fill) return false

        return true
    }

    override fun hashCode(): Int {
        var result = kettle.hashCode()
        result = 31 * result + fill
        return result
    }


}

class KettleNode(val state1: KettleState, val state2: KettleState) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is KettleNode) return false

        if (state1 != other.state1) return false
        if (state2 != other.state2) return false

        return true
    }

    override fun hashCode(): Int {
        var result = state1.hashCode()
        result = 31 * result + state2.hashCode()
        return result
    }

    override fun toString(): String {
        return "${state1.fill}, ${state2.fill}"
    }
}

fun main() {
//    println(canMeasureWater(34, 5, 6))
    println(canMeasureWater(13, 11, 1))
//    println(canMeasureWater(3, 4, 5))
//    println(canMeasureWater(2, 6, 5))
}