<template>
    <div id="form">
        <form @submit.prevent="addTask">
            <div>
                <textarea v-model="task.description">
                    Описание
                </textarea>
            </div>
            <div>
                <select v-model.number="task.performer">
                    <option :value="performer.id" v-for="performer in $store.state.performers" :key="performer.id">
                        {{performer.name}}
                    </option>
                </select>
            </div>
            <div>
                <input type="number" min="0" max="10" v-model="task.priority">
            </div>
            <div>
                <input type="submit" value="Добавить задачу">
            </div>
        </form>
    </div>
</template>

<script>
    export default {
        name: "TaskForm",
        methods: {
            async addTask() {
                await fetch(`${process.env.VUE_APP_API_URL}/tasks`, {
                    method: "POST",
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(this.task)
                })
                this.$emit('update');
            }
        },
        data: function () {
            return {
                task: {
                    description: '',
                    priority: 0,
                    performer: 0,
                }
            }
        }
    }
</script>

<style scoped>
    #form {
        display: inline-block;
        padding: 10px;
        box-shadow: 1px -2px 5px 0px rgba(0,0,0,0.75);
    }

    #form > form > div {
        padding: 5px;
    }

    input, select, textarea {
        width: 100%;
    }
</style>