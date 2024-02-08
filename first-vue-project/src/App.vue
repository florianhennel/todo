<script setup lang="ts">
import ToDo from './components/ToDo.vue';
import { ref, watch } from 'vue';

export interface ToDoInterface{
    taskId:number, 
    task: string,
}
const tasks = ref<ToDoInterface[]>([]);
const input = ref<string>("");
const url:string = "http://localhost:8080/getTasks";
const getTasks = async ()=>{
  try{
    const response = await fetch(url,{
      method:"GET",
    });
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    const json = await response.json();
    console.log(json);
    tasks.value = json;
  }catch(err){
    console.error("Error fetching data: ", err);
  }
}
getTasks(); 
watch(input,(newVal)=>{
  input.value = newVal;
})
const addTask = async ()=>{
  try{
    const response = await fetch("http://localhost:8080/addTask",{
      method:"POST",
      headers:{
        'Content-Type': 'application/json'
      },
      body:JSON.stringify({
        "task":input.value
      })
    });
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    const json = await response.json();
    if (json) {
      console.log(json);
      tasks.value.push({
        taskId:json.taskId,
        task:json.task,
      })
      input.value = "";
    }
  }catch(err){
    console.error("Error adding new task: ", err);
  }
}
</script>

<template>
  <div v-for="t in tasks" id="tasks">
    <ToDo :taskId="t.taskId" :task="t.task" />
  </div>
  <div>
    <input type="text" name="addToDo" id="addToDo" v-model="input">
    <button @click="addTask">Add new task!</button>
  </div>

</template>
<style scoped>
#tasks{
  display: flex;
  flex-direction: column;
  gap: 2rem;
}
</style>
