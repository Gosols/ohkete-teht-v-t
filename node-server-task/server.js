const { fetchData } = require("./src/data")
const express = require("express")
const app = express()
const port = 3000

async function runApp() {
    const usersURL = "https://jsonplaceholder.typicode.com/users"
    const postsURL = "https://jsonplaceholder.typicode.com/posts"

    // needs to be awaited, because fetchData is too fast to wait for a promise's response
    const userData = await fetchData(usersURL) // data about users
    const postsData = await fetchData(postsURL)

    // data about user posts

    let html = "" // the html, which will be sent after it's completed

    app.get("/", (_, res) => {
        userData.map((user) => {
            html += `<h2>${user.username} (${user.name})</h2>\n\n`

            postsData.map(post => {
                if (post.userId == user.id) {
                    html += `<li>${post.body}</li>\n`
                }
                html += "\n"
            })

        })

        res.send(html)
    })

    app.listen(port, () => {
        console.log((`App running on http://localhost:${port}`))
    })
}

runApp()

