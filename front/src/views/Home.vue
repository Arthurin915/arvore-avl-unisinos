<template>
  <main>
    <section> 
      <b-form-file
        v-model="file"
        placeholder="Escolha um arquivo para inserir em nossa árvore..."
        drop-placeholder="Deixe seu arquivo aqui!"
        browse-text="Procurar">
      </b-form-file>
      <b-button class="mt-3" variant="success" :disabled="!file" @click="readFile()">Enviar arquivo</b-button>
    </section>
    <section class="d-flex align-items-end justify-content-center mt-3">
      <div v-if="selectedFilter != 'birthdate'">
        <div>
          <label for="selectFilter">Filtrar por:</label>
          <b-form-select v-model="selectedFilter" :options="options" id="selectFilter" @change="resetFilter(); getTree()"></b-form-select>
        </div>
        <div class="d-flex align-items-end">
          <b-input-group class="mt-3" @keyup.enter="filterTree()">
            <b-form-input v-model="inputFilter" placeholder="Valor que deseja filtrar"></b-form-input>
            <b-input-group-append>
              <b-button variant="primary" :disabled=" !selectedFilter" @click="filterTree()">Filtrar</b-button>
            </b-input-group-append>
          </b-input-group>
        </div>
      </div>
      <div v-else>
        <div>
          <label for="selectFilter">Filtrar por:</label>
          <b-form-select v-model="selectedFilter" :options="options" id="selectFilter" @change="resetFilter(); getTree()"></b-form-select>
        </div>
        <div class="birthdate-inputs" >
          <div>
            <label for="startBirthdateFilter">Data de Inicio:</label>
            <v-date-picker v-model="start" id="startBirthdateFilter" class="form-control"/>
          </div>
          <div class="mx-3">
            <label for="endBirthdateFilter">Data Final:</label>
            <v-date-picker v-model="end" id="endBirthdateFilter" class="form-control"/>
          </div>
            <b-button variant="primary" @click="filterTree()">Filtrar</b-button>
        </div>
      </div>
      
      
    </section>
    <section class="tree-container" v-if="arvore">
      <h3>
        Primeiros níveis da árvore de {{getFilterName}}:
      </h3>
      <div class="level-1">
        <div v-if="arvore && arvore.key" class="">
          <node>
            <div v-if="selectedFilter=='birthdate'">
              {{arvore.key.value | moment('DD/MM/YYYY')}}
            </div>
            <div v-else>
              {{arvore.key.value}}
            </div>
          </node>
        </div>
      </div>
      <div class="level-2">
        <node v-if="arvore && arvore.left">
          <div v-if="selectedFilter=='birthdate'">
            {{arvore.left.key.value | moment('DD/MM/YYYY')}}
          </div>
          <div v-else>
            {{arvore.left.key.value}}
          </div>
        </node>
        <empty-node v-else/>
        <node v-if="arvore && arvore.right">
          <div v-if="selectedFilter=='birthdate'">
            {{arvore.right.key.value | moment('DD/MM/YYYY')}}
          </div>
          <div v-else>
            {{arvore.right.key.value}}            
          </div>
        </node> 
        <empty-node v-else/>
      </div>
      <div class="level-3">
        <div class="left-children-container">
          <div class="left-left-children mr-4">
            <node v-if="arvore && arvore.left && arvore.left.left">
              <div v-if="selectedFilter=='birthdate'">
                {{arvore.left.left.key.value | moment('DD/MM/YYYY')}}
              </div>
              <div v-else>
                {{arvore.left.left.key.value}}
              </div>
            </node>
            <empty-node v-else/>
          </div>
          <div class="left-right-children">
            <node v-if="arvore && arvore.left && arvore.left.right">
              <div v-if="selectedFilter=='birthdate'">
                {{arvore.left.right.key.value | moment('DD/MM/YYYY')}}
              </div>
              <div v-else>
                {{arvore.left.right.key.value}}
              </div>
            </node>   
            <empty-node v-else/>
          </div>
        </div>
        <div class="right-children-container">
          <div class="right-left-children mr-4">
            <node v-if="arvore && arvore.right && arvore.right.left">
              <div v-if="selectedFilter=='birthdate'">
                {{arvore.right.left.key.value | moment('DD/MM/YYYY')}}
              </div>
              <div v-else>
                {{arvore.right.left.key.value}}
              </div>
            </node> 
            <empty-node v-else/>
          </div>
          <div class="right-right-children">
            <node v-if="arvore && arvore.right && arvore.right.right">
              <div v-if="selectedFilter=='birthdate'">
                {{arvore.right.right.key.value | moment('DD/MM/YYYY')}}
              </div>
              <div v-else>
                {{arvore.right.right.key.value}}   
              </div>
            </node>
            <empty-node v-else/>
          </div>
        </div>
      </div>
    </section>
    <section class="tree-container" v-else-if="!arvore && filtering">
      <h3 v-if="filterResults.length == 0">Nenhum resultado encontrado</h3>
      <div v-else>
        <h3>Resultados encontrados:</h3>
        <div class='d-flex flex-wrap mt-3' v-if="selectedFilter == 'name'">
          <div v-for="(node, index) in filterResults" :key="index" class="mr-3 d-flex">
            <div class="result-container">
              <div>Nome: {{pessoas[node.index].name}}</div>
              <div class="ml-2">CPF: {{pessoas[node.index].cpf}}</div>
              <div class="ml-2">RG: {{pessoas[node.index].rg}}</div>
              <div class="ml-2">Data de Nascimento: {{pessoas[node.index].dataNascimento | moment('DD/MM/YYYY')}}</div>
              <div class="ml-2">Cidade: {{pessoas[node.index].cidadeNascimento}}</div>
            </div>
          </div>
        </div>
        <div v-else-if="selectedFilter == 'cpf'">
          <div class="result-container">
            <div>Nome: {{pessoas[filterResults.index].name}}</div>
            <div class="ml-2">CPF: {{pessoas[filterResults.index].cpf}}</div>
            <div class="ml-2">RG: {{pessoas[filterResults.index].rg}}</div>
            <div class="ml-2">Data de Nascimento: {{pessoas[filterResults.index].dataNascimento}}</div>
            <div class="ml-2">Cidade: {{pessoas[filterResults.index].cidadeNascimento}}</div>
          </div>
            
          
        </div>
        <div v-else-if="selectedFilter == 'birthdate'">
          <div class='d-flex flex-wrap mt-3'>
            <div v-for="(node, index) in filterResults" :key="index" class="mr-3 d-flex">
              <div class="result-container">
                <div>Nome: {{pessoas[node.index].name}}</div>
                <div class="ml-2">CPF: {{pessoas[node.index].cpf}}</div>
                <div class="ml-2">RG: {{pessoas[node.index].rg}}</div>
                <div class="ml-2">Data de Nascimento: {{pessoas[node.index].dataNascimento | moment('DD/MM/YYYY') }}</div>
                <div class="ml-2">Cidade: {{pessoas[node.index].cidadeNascimento}}</div>
              </div>

            </div>
          </div>
        </div>
      </div>
    </section>
    <marquee v-if="alienDancando">
      <img src="https://media.tenor.com/images/026e2ff33b229671d579dd512dcac521/tenor.gif" alt="">
    </marquee>
  </main>
</template>

<script>
import Node from "@/components/Node.vue"
import EmptyNode from "@/components/EmptyNode.vue"
export default {
  name: 'home',
  components: {
    Node,
    EmptyNode
  },
  data: () => ({
    file: null,
    timer: null,
    alienDancando: false,
    pessoas: [],
    start: new Date(),
    end: new Date(),
    filtering: false,
    filterResults: null,
    pessoaArr: [],
    selectedFilter: 'name',
    inputFilter: null,
    options: [
      { value: 'name', text: 'Nome' },
      { value: 'cpf', text: 'CPF' },
      { value: 'birthdate', text: 'Data de Nascimento' },
    ],
    arvore: null
  }),
  computed: {
    getFilterName() {
      switch(this.selectedFilter) {
        case 'name':
          return 'Nome'
        case 'cpf':
          return 'CPF'
        case 'birthdate':
          return 'Data de Aniversário'
      }
    }
  },
  methods: {
    readFile() {
      if(this.file) {
        let reader = new FileReader()
        reader.onload = event => {
          this.pessoaArr = []
          let lines = event.target.result.split('\n');
          this.clearTree()
          for(let i = 0; i < lines.length;i++) {
            let lineData = lines[i].split(';');
            if(lineData.includes('') || lineData.length < 5) return false
            let dataNascimentoArr = lineData[3].split('/');
            let dataNascimento = `${dataNascimentoArr[2].trim()}-${dataNascimentoArr[1].trim()}-${dataNascimentoArr[0].trim()}`
            this.pessoaArr.push({
              cpf: lineData[0].trim(),
              rg: lineData[1].trim(),
              name: lineData[2].trim(), 
              dataNascimento: dataNascimento,
              cidadeNascimento: lineData[4].trim(), 
            })
          }
          this.file = null
          this.sendFileData()
        }
        reader.readAsText(this.file);
      }
    },
    async sendFileData() {
      try {
        const res = await global.$post("/people", this.pessoaArr)
        this.alienDancando = true
        this.getPeople()        
      } catch(err) {
        console.log(err)
        alert('erro!')
      } finally {
        this.timer = setTimeout(() => {
          this.alienDancando = false
          this.getTree()
        }, 5000)
      }
    },
    async filterTree() {
      const requestParam = this.getRequestParam()
      if(!requestParam) return false
      if(((this.selectedFilter == 'name' || this.selectedFilter == 'cpf') &&  !this.inputFilter) || this.selectedFilter == 'birthdate' && (!this.start && !this.end) ) {
        this.getTree()
        return false
      }
      try {
        const res = await global.$get(`/tree/filter-${this.selectedFilter}${requestParam}`)
        this.filterResults = res.data
        this.filtering = true
        this.arvore = null
      } catch(err) {
        alert('erro!', err)
      } 
    },
    async clearTree() {
      try {
        await global.$post("/clear")
      } catch(err) {
        alert('erro!', err)       
      } 
    },
    async getPeople() {
      try {
        const res = await global.$get('/people')
        this.pessoas = res.data
      } catch(err) {
        alert('erro!', err)       
      } 
    },async getTree() {
      try {
        const res = await global.$get(`/tree/${this.selectedFilter}`)
        this.arvore = res.data.root
      } catch(err) {
        alert('erro!', err)       
      } 
    },
    resetFilter() {
      this.inputFilter = null
      for (const key in this.range)
        this.range[key] = new Date()
      this.filtering = false
    },
    getRequestParam() {
      debugger
      switch(this.selectedFilter) {
        case 'cpf': 
          return `?cpf=${this.inputFilter}`
        case 'birthdate':
          return `?startDate=${this.$moment(this.start).format('YYYY-MM-DD')}&endDate=${this.$moment(this.end).format('YYYY-MM-DD')}`
        case 'name': 
          return `?name=${this.inputFilter}`
      }
    }
  },
  mounted() {
    // this.getTree()
    this.clearTree()
  }
}
</script>
<style lang="scss" scoped>
.birthdate-inputs {
  display: flex;
  align-items: flex-end;
}
.tree-container {
  color: black;
  border-radius: .25rem;
  background-color: white;
  padding: 1rem;
  margin-top: 3rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  .level-1 {
    margin: 0 auto;
  }
  .level-2 {
    display: flex;
    justify-content: space-between;
    width: 50%;
  }
  .level-3 {
    margin-top: 2rem;
    display: flex;
    justify-content: space-between;
    width: 65%;
    .left-children-container {
      display: flex;
      width: 20%;
    }
    .right-children-container {
      display: flex;
      width: 40%;
    }
  }
@media (max-width: 1024px) {
  .level-2 {
    width: 60%;
  }
  .level-3 {
    width: 75%;
    .left-children-container {
      width: 35%;
    }
    .right-children-container {
      width: 35%;
    } 
  }
}
@media (max-width: 768px) {
  .level-2 {
    width: 70%;
  }
  .level-3 {
    width: 90%;
    .left-children-container {
      width: 45%;
    }
    .right-children-container {
      width: 45%;
    } 
  }
}
}
.result-container {
  background-color: #dbdbdb;
  padding: 1rem;
  margin: 10px;
}
</style>
