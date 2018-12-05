<template>
  <section>
    <div class="modal-card" style="width: auto">
      <header class="modal-card-head">
        <p class="modal-card-title">角色用户管理</p>
      </header>
      <section class="modal-card-body">
        <b-table :bordered="true" :striped="true" :hoverable="true" :focusable="true" :loading.sync="loading"
                 :data="respBody.users"
                 :paginated="true" :currentPage.sync="reqBody.pageNo" :perPage="reqBody.pageSize"
                 :total="respBody.total">
          <template slot-scope="props">
            <b-table-column field="id" label="ID" width="40" numeric>
              {{ props.row.id }}
            </b-table-column>
          </template>
        </b-table>
      </section>
      <footer class="modal-card-foot">
        <button class="button" type="button" @click="$parent.close()">Close</button>
      </footer>
    </div>
  </section>
</template>

<script>
  import store from '@/store'

  export default {
    name: 'RoleUserModal',
    props: {
      role: {type: Object, required: true}
    },
    data () {
      return {
        loading: false,
        reqBody: {
          roleId: this.role.id,
          pageNo: 1,
          pageSize: 10
        },
        respBody: {
          users: [],
          total: 0
        }
      }
    },
    methods: {},
    beforeMount () {
      this.loading = true
      store.dispatch('getRoleUsers', this.reqBody)
        .then((resp) => {
          console.log(resp)
          this.loading = false
          this.respBody.users = resp.data.content
          this.respBody.total = resp.data.totalElements
        })
    }
  }
</script>

<style scoped>

</style>
