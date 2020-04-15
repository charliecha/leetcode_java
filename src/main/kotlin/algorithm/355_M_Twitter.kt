package algorithm

import java.util.*

/**
设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近十条推文。你的设计需要支持以下的几个功能：

postTweet(userId, tweetId): 创建一条新的推文
getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
follow(followerId, followeeId): 关注一个用户
unfollow(followerId, followeeId): 取消关注一个用户
示例:

Twitter twitter = new Twitter();

// 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
twitter.postTweet(1, 5);

// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
twitter.getNewsFeed(1);

// 用户1关注了用户2.
twitter.follow(1, 2);

// 用户2发送了一个新推文 (推文id = 6).
twitter.postTweet(2, 6);

// 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
// 推文id6应当在推文id5之前，因为它是在5之后发送的.
twitter.getNewsFeed(1);

// 用户1取消关注了用户2.
twitter.unfollow(1, 2);

// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
// 因为用户1已经不再关注用户2.
twitter.getNewsFeed(1);

数据类，数据类的创建和业务分离。

依赖反转，User和NewsFeed的创建和逻辑做了分离

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/design-twitter
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Twitter {
    private val userMap = mutableMapOf<Int, User>()

    private val newsFeedMap = mutableMapOf<Int, NewsFeed>()

    private fun getOrCreateUser(userId: Int): User {
        val user = userMap[userId]
        return if (user != null) {
            user
        } else {
            val u = User(userId)
            userMap[userId] = u
            u
        }
    }

    private fun getOrCreateNewsFeed(newsFeedId: Int): NewsFeed {
        val newsFeed = newsFeedMap[newsFeedId]
        return if (newsFeed != null) {
            newsFeed
        } else {
            val nf = NewsFeed(newsFeedId)
            newsFeedMap[newsFeedId] = nf
            nf
        }
    }

    /** Initialize your data structure here. */
    class User(val userId: Int) {
        val followeeList = mutableSetOf<User>()
        val newsFeeds = mutableSetOf<NewsFeed>()

        fun follow(followee: User) {
            followeeList.add(followee)
        }

        fun unfollow(followee: User) {
            followeeList.remove(followee)
        }

        fun postTweet(newsFeed : NewsFeed) {
            newsFeeds.add(newsFeed)
        }
    }

    class NewsFeed(val tweetId: Int) {
        val timestamp: Long = System.nanoTime()
    }


    /** Compose a new tweet. */
    fun postTweet(userId: Int, tweetId: Int) {
        getOrCreateUser(userId).postTweet(getOrCreateNewsFeed(tweetId))
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    fun getNewsFeed(userId: Int): List<Int> {
        val user = getOrCreateUser(userId)

        val newsFeedList = mutableListOf<NewsFeed>()

        for (newsFeed in user.newsFeeds) {
            newsFeedList.add(newsFeed)
        }

        for (followee in user.followeeList) {
            if (followee == user) {
                continue
            }

            for (newsFeed in followee.newsFeeds) {
                newsFeedList.add(newsFeed)
            }
        }

        newsFeedList.sortWith(Comparator { o1: NewsFeed, o2: NewsFeed -> o2.timestamp.compareTo(o1.timestamp) })

        while(newsFeedList.size > 10) {
            newsFeedList.removeAt(newsFeedList.size - 1)
        }

        return newsFeedList.map { it.tweetId }
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    fun follow(followerId: Int, followeeId: Int) {
        getOrCreateUser(followerId).follow(getOrCreateUser(followeeId))
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    fun unfollow(followerId: Int, followeeId: Int) {
        getOrCreateUser(followerId).unfollow(getOrCreateUser(followeeId))
    }

}


/**
 * Your Twitter object will be instantiated and called as such:
 * var obj = Twitter()
 * obj.postTweet(userId,tweetId)
 * var param_2 = obj.getNewsFeed(userId)
 * obj.follow(followerId,followeeId)
 * obj.unfollow(followerId,followeeId)
 */

fun main() {
    val twitter = Twitter()

// 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
    twitter.postTweet(1, 5)

// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
    twitter.getNewsFeed(1)

// 用户1关注了用户2.
    twitter.follow(1, 2)

// 用户2发送了一个新推文 (推文id = 6).
    twitter.postTweet(2, 6)

// 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
// 推文id6应当在推文id5之前，因为它是在5之后发送的.
    twitter.getNewsFeed(1)

    println(Arrays.toString(twitter.getNewsFeed(1).toIntArray()))

// 用户1取消关注了用户2.
    twitter.unfollow(1, 2)

// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
// 因为用户1已经不再关注用户2.
    twitter.getNewsFeed(1)

    println(Arrays.toString(twitter.getNewsFeed(1).toIntArray()))
}