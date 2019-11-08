<template>
  <div id="app">
    <main class="container">
      <h1>AVL - Boys</h1>
      <router-view/>
    </main>
  </div>
</template>

<style lang="scss">
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #FFFF;
  background-image: url("assets/arvore.png");
  background-repeat: repeat;
  background-size: 200px 200px;
  padding: 2rem;
  .container {
    border-radius: .25rem;
    background-color: #585858;
  }
}
</style>
