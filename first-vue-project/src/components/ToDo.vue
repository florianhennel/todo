<script setup lang="ts">
import {ToDoInterface} from "../App.vue";
import { ref, watch } from "vue";
const props = defineProps<ToDoInterface>();
const doneCheckBox = ref(props.done);

watch(doneCheckBox,(newVal)=>{
doneCheckBox.value = newVal;
console.log(doneCheckBox.value);
})
</script>


<template>
<div :class=" doneCheckBox.valueOf()?'todo done':'todo'">
    <div class="id">{{ taskId }}.</div>
    <div class="task">{{ task }}</div>
    <input type="checkbox" value="{{ done }}" v-model="doneCheckBox">
</div>
</template>


<style scoped>
.todo{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    background-color: azure;
    color: black;
    border-radius: 2rem;
    padding: 1rem;
    font-size: xx-large;  
}
.task{
    display: flex;
    flex-wrap: wrap;
    width: 75%;
    overflow-wrap: break-word;
    word-wrap: break-word;
    word-break: break-all;
}
.done{
    background-color: green;
}
input{
    width: 1.5rem;
    height: auto;
    aspect-ratio: 1;
}
</style>