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
            {{arvore.key.value}}
          </node>
        </div>
      </div>
      <div class="level-2">
        <node v-if="arvore && arvore.left">
          {{arvore.left.key.value}}
        </node>
        <empty-node v-else/>
        <node v-if="arvore && arvore.right">
          {{arvore.right.key.value}}            
        </node> 
        <empty-node v-else/>
      </div>
      <div class="level-3">
        <div class="left-children-container">
          <div class="left-left-children mr-4">
            <node v-if="arvore && arvore.left && arvore.left.left">
              {{arvore.left.left.key.value}}
            </node>
            <empty-node v-else/>
          </div>
          <div class="left-right-children">
            <node v-if="arvore && arvore.left && arvore.left.right">
              {{arvore.left.right.key.value}}
            </node>   
            <empty-node v-else/>
          </div>
        </div>
        <div class="right-children-container">
          <div class="right-left-children mr-4">
            <node v-if="arvore && arvore.right && arvore.right.left">
              {{arvore.right.left.key.value}}
            </node> 
            <empty-node v-else/>
          </div>
          <div class="right-right-children">
            <node v-if="arvore && arvore.right && arvore.right.right">
              {{arvore.right.right.key.value}}   
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
          <node v-for="(node, index) in filterResults" :key="index" class="mr-3">
            {{node.value}}
          </node>
        </div>
        <div v-else-if="selectedFilter == 'cpf'">
          <node>
            {{filterResults ? filterResults.value : ''}}
          </node>
        </div>
        <div v-else-if="selectedFilter == 'birthdate'">
          <div class='d-flex flex-wrap mt-3'>
            <node v-for="(node, index) in filterResults" :key="index" class="mr-3">
              {{node.value}}
            </node>
          </div>
        </div>
      </div>
    </section>
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
        alert('Arquivo enviado com sucesso')
        this.getTree()
      } catch(err) {
        console.log(err)
        alert('erro!')

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
    async getTree() {
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
    width: 40%;
  }
  .level-3 {
    margin-top: 2rem;
    display: flex;
    justify-content: space-between;
    width: 50%;
    .left-children-container {
      display: flex;
      width: 35%;
    }
    .right-children-container {
      display: flex;
      width: 35%;
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
</style>
