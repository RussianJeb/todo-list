<template>
    <div id="app">
        <h1>TODO APP</h1>
        <div id="tasks">
            <task-form @update="fetchData"></task-form>
            <div id="task-info">
                <div v-for="item in orderedTasks"
                     :key="item.id">
                    <task-info  @update="fetchData"
                            :task="item"/>
                </div>
            </div>

        </div>
    </div>
</template>

<script>
    import TaskInfo from "./components/TaskInfo";
    import TaskForm from "./components/TaskForm";

    export default {
        name: 'App',
        components: {
            TaskForm,
            TaskInfo,
        },
        created() {
          this.fetchData()
        },
        methods: {
            async fetchData() {
                await this.$store.dispatch('findTasks')
                await this.$store.dispatch('findPerformers')
            }
        },
        computed: {
            orderedTasks() {
                return this.$store.state.tasks.slice().sort(function (taskA, taskB) {
                    return taskB.priority - taskA.priority;
                })
            }
        }
    }
</script>

<style>
    #tasks {
        display: block;
        text-align: center;
    }

    #task-info {
        display: block;
        padding-top: 20px;
    }
    #task-info > div {
        display: block;
        padding: 10px 0px;
    }
</style>
