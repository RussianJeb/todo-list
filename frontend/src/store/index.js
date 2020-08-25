import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        tasks: [],
        performers: []
    },
    mutations: {
        setTasks(state, payload) {
            state.tasks = payload;
        },
        setPerformers(state, payload) {
            state.performers = payload;
        }
    },
    actions: {
        async findTasks({commit}) {
            let tasks = await fetch(`${process.env.VUE_APP_API_URL}/tasks`, {
                method: "GET"
            }).then(stream => stream.json());
            commit('setTasks', tasks)
        },
        async findPerformers({commit}) {
            let tasks = await fetch(`${process.env.VUE_APP_API_URL}/performers`, {
                method: "GET"
            }).then(stream => stream.json());
            commit('setPerformers', tasks)
        }
    },
    modules: {}
})
