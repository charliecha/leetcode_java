package algorithm

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.LinkedHashMap

/**
 * 设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put。

get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
put(key, value) - 如果键不存在，请设置或插入值。当缓存达到其容量时，它应该在插入新项目之前，使最不经常使用的项目无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除。

进阶：
你是否可以在 O(1) 时间复杂度内执行两项操作？

示例：

LFUCache cache = new LFUCache( 2 /* capacity (缓存容量) */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // 返回 1
cache.put(3, 3);    // 去除 key 2
cache.get(2);       // 返回 -1 (未找到key 2)
cache.get(3);       // 返回 3
cache.put(4, 4);    // 去除 key 1
cache.get(1);       // 返回 -1 (未找到 key 1)
cache.get(3);       // 返回 3
cache.get(4);       // 返回 4

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/lfu-cache
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class LFUCache(val capacity: Int) {
    private val valueMap = HashMap<Int, Node>()
    private val freqMap = LinkedHashMap<Int, MutableList<Int>>()

    private fun addKey(freq: Int, key: Int) {
        val list = freqMap[freq]
        if (list == null) {
            freqMap[freq] = mutableListOf(key)
        } else {
//            if(list.contains(key)) {
//                println("duplicate $freq:$key")
//            }
            list.add(key)
        }


//        println("addKey $freq:$key -> ${Arrays.toString(freqMap[freq]?.toIntArray())}")
    }

    private fun removeKey(freq: Int, key: Int) {
        val list = freqMap[freq] ?: return

        list.remove(key)
        if (list.isEmpty()) {
            freqMap.remove(freq)
        }

//        println("removeKey $freq:$key -> ${Arrays.toString(freqMap[freq]?.toIntArray())}")
    }

    fun get(key: Int): Int {
//        println("cache.get($key)")
//        if (valueMap[key] == null) {
//            println(-1)
//        }

        val node = valueMap[key] ?: return -1

        removeKey(node.freq, key)
        addKey(node.freq + 1, key)
        node.freq = node.freq + 1

//        println(node.value)
        return node.value ?: -1
    }

    fun put(key: Int, value: Int) {
//        println("cache.put($key, $value)")

        if (capacity == 0) {
            return
        }

        if (valueMap.size >= capacity && !valueMap.containsKey(key)) {
            val minFreq = freqMap.keys.min() ?: return

            val oldestKey = freqMap[minFreq]!![0]
//            println("delete $minFreq $oldestKey")
            removeKey(minFreq, oldestKey)
            valueMap.remove(oldestKey)
        }

        val node = valueMap[key]
        if (node == null) {
            addKey(1, key)
            valueMap[key] = Node(value, 1)
        } else {
            removeKey(node.freq, key)
            addKey(node.freq + 1, key)

            node.value = value
            node.freq += 1
        }
    }

    class Node(var value: Int, var freq: Int)
}

fun main() {
    val cache = LFUCache(10 /* capacity (缓存容量) */);

//    cache.put(1, 1);
//    cache.put(2, 2);
//    cache.get(1);       // 返回 1
//    cache.put(3, 3);    // 去除 key 2
//    cache.get(2);       // 返回 -1 (未找到key 2)
//    cache.get(3);       // 返回 3
//    cache.put(4, 4);    // 去除 key 1
//    cache.get(1);       // 返回 -1 (未找到 key 1)
//    cache.get(3);       // 返回 3
//    cache.get(4);       // 返回 4

    cache.put(10, 13)
    cache.put(3, 17)
    cache.put(6, 11)
    cache.put(10, 5)
    cache.put(9, 10)
    cache.get(13)
    cache.put(2, 19)
    cache.get(2)
    cache.get(3)
    cache.put(5, 25)
    cache.get(8)
    cache.put(9, 22)
    cache.put(5, 5)
    cache.put(1, 30)
    cache.get(11)
    cache.put(9, 12)
    cache.get(7)
    cache.get(5)
    cache.get(8)
    cache.get(9)
    cache.put(4, 30)
    cache.put(9, 3)
    cache.get(9)
    cache.get(10)
    cache.get(10)
    cache.put(6, 14)
    cache.put(3, 1)
    cache.get(3)
    cache.put(10, 11)
    cache.get(8)
    cache.put(2, 14)
    cache.get(1)
    cache.get(5)
    cache.get(4)
    cache.put(11, 4)
    cache.put(12, 24)
    cache.put(5, 18)
    cache.get(13)
    cache.put(7, 23)
    cache.get(8)
    cache.get(12)
    cache.put(3, 27)
    cache.put(2, 12)
    cache.get(5)
    cache.put(2, 9)
    cache.put(13, 4)
    cache.put(8, 18)
    cache.put(1, 7)
    cache.get(6)
    cache.put(9, 29)
    cache.put(8, 21)
    cache.get(5)
    cache.put(6, 30)
    cache.put(1, 12)
    cache.get(10)
    cache.put(4, 15)
    cache.put(7, 22)
    cache.put(11, 26)
    cache.put(8, 17)
    cache.put(9, 29)
    cache.get(5)
    cache.put(3, 4)
    cache.put(11, 30)
    cache.get(12)
    cache.put(4, 29)
    cache.get(3)
    cache.get(9)
    cache.get(6)
    cache.put(3, 4)
    cache.get(1)
    cache.get(10)
    cache.put(3, 29)
    cache.put(10, 28)
    cache.put(1, 20)
    cache.put(11, 13)
    cache.get(3)
    cache.put(3, 12)
    cache.put(3, 8)
    cache.put(10, 9)
    cache.put(3, 26)
    cache.get(8)
    cache.get(7)
    cache.get(5)
    cache.put(13, 17)
    cache.put(2, 27)
    cache.put(11, 15)
    cache.get(12)
    cache.put(9, 19)
    cache.put(2, 15)
    cache.put(3, 16)
    cache.get(1)
    cache.put(12, 17)
    cache.put(9, 1)
    cache.put(6, 19)
    cache.get(4)
    cache.get(5)
    cache.get(5)
    cache.put(8, 1)
    cache.put(11, 7)
    cache.put(5, 2)


//    ["LFUCache","put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"]
//    [[10],[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],[1,30],[11],[9,12],[7],[5],[8],[9],[4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],[8],[2,14],[1],[5],[4],[11,4],[12,24],[5,18],[13],[7,23],[8],[12],[3,27],[2,12],[5],[2,9],[13,4],[8,18],[1,7],[6],[9,29],[8,21],[5],[6,30],[1,12],[10],[4,15],[7,22],[11,26],[8,17],[9,29],[5],[3,4],[11,30],[12],[4,29],[3],[9],[6],[3,4],[1],[10],[3,29],[10,28],[1,20],[11,13],[3],[3,12],[3,8],[10,9],[3,26],[8],[7],[5],[13,17],[2,27],[11,15],[12],[9,19],[2,15],[3,16],[1],[12,17],[9,1],[6,19],[4],[5],[5],[8,1],[11,7],[5,2],[9,28],[1],[2,2],[7,4],[4,22],[7,24],[9,26],[13,28],[11,26]]
}


