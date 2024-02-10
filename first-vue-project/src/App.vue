<script setup lang="ts">
import ToDo from './components/ToDo.vue';
import { ref, watch } from 'vue';

export interface ToDoInterface{
    taskId:number, 
    task: string,
    done:boolean,
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
      tasks.value.push({
        taskId:json.taskId,
        task:json.task,
        done:json.done,
      })
      input.value = "";
    }
  }catch(err){
    console.error("Error adding new task: ", err);
  }
}
</script>

<template>
  <div class="tasks">
    <ToDo v-for="t in tasks" v-bind="t" />
  </div>
  
  <div class="addTask">
    <input type="text" class="textInput" v-model="input">
    <button @click="addTask">Add new task!</button>
  </div>

</template>
<style scoped>
.tasks{
  display: flex;
  padding: 5%;
  border-radius: 30px;
  background-color: #343434;
  margin-bottom: 1rem;
  flex-direction: column;
  gap: 2rem;
  width: 33%;
}
.textInput{
  padding: 1rem;
  height: 1em;
  font-size: larger;
}
.addTask{
  display: flex;
  flex-direction: column;
  align-items: center;
  padding:1rem;
}
</style>
