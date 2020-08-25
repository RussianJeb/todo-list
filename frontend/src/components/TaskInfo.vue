<template>
    <div id="info">
        <div>
            {{task.description}}
        </div>
        <div>
            {{task.performer}}
        </div>
        <div>
            {{task.priority}}
            <button @click="deleteTask">
                Удалить
            </button>
            <button v-if="task.priority < 10" @click="changePriority('INCREMENT')">
                Повысить приоритет
            </button>
            <button v-if="task.priority > 0" @click="changePriority('DECREMENT')">
                Понизить приоритет
            </button>
        </div>
    </div>
</template>

<script>
    export default {
        name: "TaskInfo",
        props: {
            task: Object,
        },
        methods: {
            async changePriority(operation) {
                let body = {
                    operation: operation
                }
                await fetch(`${process.env.VUE_APP_API_URL}/tasks/priority/${this.task.id}`, {
                    method: "PUT",
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(body)
                });
                this.$emit('update');
            },
            async deleteTask() {
                await fetch(`${process.env.VUE_APP_API_URL}/tasks/${this.task.id}`, {
                    method: "DELETE",
                });
                this.$emit('update');
            }
        }
    }
</script>

<style scoped>
    #info {
        display: inline-block;
        padding: 10px;
        box-shadow: 1px -2px 5px 0px rgba(0,0,0,0.75);
    }

    #info > div {
        padding: 5px 0;
    }

</style>