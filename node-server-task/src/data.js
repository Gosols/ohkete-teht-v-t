const fetch = require("node-fetch")


// fetch json data from url, and return it as an array?
async function fetchData(url) {

    const data = await fetch(url)
    const json = await data.json()

    return json
}
// for testing
//console.log(fetchData("https://jsonplaceholder.typicode.com/users"))
//fetchData("https://jsonplaceholder.typicode.com/users")

module.exports = { fetchData }


