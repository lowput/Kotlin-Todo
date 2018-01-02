import kotlinx.html.*
import kotlinx.html.stream.createHTML
import spark.Spark.get

val tasks = ArrayList<String>()

fun createSimpleHTML() = createHTML().html {
    body {
        h1 { +"Simple Todo list" }
        table {
            tasks.forEach {
                tr {
                    td {
                        checkBoxInput {
                            +it
                        }
                    }
                }
            }
        }
        br
        form(action = "/set", method = FormMethod.get) {
            input(type = InputType.text, name = "task")
            submitInput {
                value = "登録"
            }
        }
    }
}

fun main(args: Array<String>) {
    get("/tasks") { req, res ->
        buildString {
            append(createSimpleHTML())
        }
    }

    get("/set") { req, res ->
        tasks.add(req.queryParams("task"))
        buildString {
            append(createSimpleHTML())
        }
    }
}
