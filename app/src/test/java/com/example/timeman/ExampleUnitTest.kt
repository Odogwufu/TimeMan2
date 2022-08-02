package com.example.timeman

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

// package com.example.timeman

// import org.junit.Test

// import org.junit.Assert.*
// import org.junit.Rule
// import org.junit.rules.TestRule


// /**
//  * Example local unit test, which will execute on the development machine (host).
//  *
//  * See [testing documentation](http://d.android.com/tools/testing).
//  */
// class ExampleUnitTest {

// //    @get:Rule
// //    var rule: TestRule = InstantTaskExecutorRule()

//     lateinit var mainActivity: MainActivity


//   var allitems  = ArrayList<String>()

//     private fun mainactivityinitialized() {
//         mainActivity = MainActivity()
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
//         assertNotNull(allitems)
//         assertTrue(allitems!!.isNotEmpty())
//         var containsfire = false
//         allitems!!.forEach {
//             if (items.contains("fire")){
//                 containsfire = true
//             }
//         }
//         assertTrue(containsfire)
//     }
// }

