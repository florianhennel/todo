<script setup lang="ts">
import {ToDoInterface} from "../App.vue";
defineProps<ToDoInterface>();


</script>


<template>
<div class="todo">
    <div class="id">{{ taskId }}.</div>
    <div class="task">{{ task }}</div>
    <input type="checkbox" name="done" id="done">
</div>
</template>


<style>
.todo{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    background-color: aqua;
    color: black;
    border-radius: 2rem;
    padding: 1rem;
    font-size: xx-large;
    gap: 1rem;
}
</style>