package org.example.interview.annotations;

import jdk.nashorn.tools.Shell;

/**
 * @author qbq
 * @date 2022/1/10 5:59 PM
 */
public enum Tag {

    /**
     * 基本
     */
    array("数组"),
    string("字符串"),
    sort("排序"),
    matrix("矩阵"),
    simulation("模拟"),
    enumeration("枚举"),
    array_match("字符串匹配"),
    bucket_sort("桶排序"),
    count_sort("计数排序"),
    radix_sort("基数排序"),

    /**
     * 算法
     */
    dynamic_programing("动态规划"),
    dfs("深度优先搜索"),
    bfs("广度优先搜索"),
    greed("贪心"),
    binary_search("二分查找"),
    backtrace("回溯"),
    recursion("递归"),
    divide_conquer("分治"),
    memory_search("记忆化搜索"),
    merge_sort("归并排序"),
    quick_choose("快速选择"),

    /**
     * 基础数据结构
     */
    hash_table("哈希表"),
    tree("树"),
    binary_tree("二叉树"),
    stack("栈"),
    heap("堆（优先队列）"),
    graph("图"),
    linked_list("链表"),
    binary_search_tree("二叉搜索树"),
    sorted_collection("有序集合"),
    queue("队列"),
    topological_sort("拓扑排序"),
    shortest_road("最短路"),
    double_linked_list("双向链表"),
    mst("最小生成树"),
    euler_circuit("欧拉回路"),
    strong_connectivity("强连通分量"),
    biconnected_component("双连通分量"),

    /**
     * 高级数据结构
     */
    union_find_sets("并查集"),
    dictionary_tree("字典树"),
    segment_tree("线段树"),
    binary_indexed_trees("树状数组"),
    suffix_array("后缀数组"),

    /**
     * 技巧
     */
    double_pointer("双指针"),
    bit_calc("位运算"),
    prefix_sum("前缀和"),
    sliding_window("滑动窗口"),
    count("计数"),
    state_compression("状态压缩"),
    hash_function("哈希函数"),
    scroll_hash("滚动哈希"),
    scanning_line("扫描线"),

    /**
     * 数学
     */
    math("数学"),
    geometry("几何"),
    game("博弈"),
    combinatorial_mathematics("组合数学"),
    randomization("随机化"),
    number_theory("数论"),
    probability_and_statistics("概率与统计"),
    reservoir_sampling("水塘抽样"),
    rejection_sampling("拒绝采样"),

    /**
     * 其他
     */
    database("数据库"),
    design("设计"),
    data_stream("数据流"),
    interaction("交互"),
    brain_twists("脑筋急转弯"),
    iterator("迭代器"),
    multi_thread("多线程"),
    shell("shell"),

    ;

    private String desc;

    Tag(String desc) {
        this.desc = desc;
    }
}
