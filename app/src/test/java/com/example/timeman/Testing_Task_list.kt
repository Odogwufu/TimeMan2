// package com.example.timeman

// import org.junit.Assert
// import org.junit.Test

// class Testing_Task_list {

//     lateinit var mainActivity: MainActivity


//     var allitems  = ArrayList<String>()

//     @Test
//     fun addition_isCorrect() {
//         Assert.assertEquals(4, 2 + 2)
//     }

//     var items= ArrayList<String>()

//     @Test
//     suspend fun Task_being_added_to_list(item : String) {

//         MainActivityInitialized()
//         ListData()
//         thenlistShouldContainfire()
//     }
//     private fun MainActivityInitialized() {
//         mainActivity = MainActivity()
//     }

//     private suspend fun ListData() {
//         var allitems = mainActivity.fetchItems()
//     }

//     private fun thenlistShouldContainfire() {
//         Assert.assertNotNull(allitems)
//         Assert.assertTrue(allitems!!.isNotEmpty())
//         var containsfire = false
//         allitems!!.forEach {
//             if (items.contains("fire")){
//                 containsfire = true
//             }
//         }
//         Assert.assertTrue(containsfire)
//     }
// }
