<template>
  <div>
    <b-table :bordered="true" :striped="true" :hoverable="true" :focusable="true" :loading.sync="loading"
             :data="listRespBody.roles"
             :paginated="true" :currentPage.sync="listReqBody.pageNo" :perPage="listReqBody.pageSize"
             :total="listRespBody.total">
      <template slot-scope="props">
        <b-table-column field="id" label="ID" width="40" numeric>
          {{ props.row.id }}
        </b-table-column>

        <b-table-column field="code" label="代号">
          {{ props.row.code }}
        </b-table-column>

        <b-table-column field="name" label="角色名称">
          {{ props.row.name }}
        </b-table-column>

        <b-table-column field="createTime" label="创建时间" centered>
          <span class="tag is-success">
            {{ props.row.createTime | formatDate }}
          </span>
        </b-table-column>

        <b-table-column label="操作">
          <p class="buttons">
            <a class="button" @click="detail(props.row.id)">
              <span class="icon is-small">
                <i class="fas fa-info"></i>
              </span>
              <span>查看</span>
            </a>
            <a class="button" @click="edit(props.row.id)">
              <span class="icon is-small">
                <i class="far fa-edit"></i>
              </span>
              <span>编辑</span>
            </a>
            <a v-if="!props.row.isSysDefined" class="button" @click="setResources(props.row.id)">
              <span class="icon is-small">
                <i class="far fa-edit"></i>
              </span>
              <span>设置权限</span>
            </a>
            <a v-if="!props.row.isSysDefined" class="button" @click="setUsers(props.row.id)">
              <span class="icon is-small">
                <i class="far fa-edit"></i>
              </span>
              <span>管理权限用户</span>
            </a>
            <a v-if="!props.row.isSysDefined" class="button" @click="del(props.row.id)">
              <span class="icon is-small">
                <i class="far fa-trash-alt"></i>
              </span>
              <span>删除</span>
            </a>
          </p>
        </b-table-column>
      </template>
    </b-table>
    <b-modal :active.sync="isShowDetailModal">
      <role-detail-modal :role="role"></role-detail-modal>
    </b-modal>
    <b-modal :active.sync="isShowEditModal">
      <role-edit-modal :role="role"></role-edit-modal>
    </b-modal>
    <b-modal :active.sync="isShowRoleResourceSetModal">
      <role-resource-set-modal :role="role" :resources="resources"></role-resource-set-modal>
    </b-modal>
    <b-modal :active.sync="isShowRoleUserModal">
      <role-user-modal :role="role"></role-user-modal>
    </b-modal>
  </div>
</template>

<script>
  import store from '@/store'
  import RoleDetailModal from '@/views/role/RoleDetailModal'
  import RoleEditModal from '@/views/role/RoleEditModal'
  import RoleResourceSetModal from '@/views/role/RoleResourceSetModal'
  import RoleUserModal from '@/views/role/RoleUserModal'

  export default {
    name: 'role-list',
    components: {RoleUserModal, RoleResourceSetModal, RoleEditModal, RoleDetailModal},
    data () {
      return {
        listReqBody: {
          pageNo: 1,
          pageSize: 10
        },
        loading: false,
        listRespBody: {
          total: 0,
          roles: []
        },
        role: {},
        resources: [],
        isShowDetailModal: false,
        isShowEditModal: false,
        isShowRoleResourceSetModal: false,
        isShowRoleUserModal: false
      }
    },
    methods: {
      listRoles () {
        this.loading = true
        store.dispatch('listRole', this.listReqBody)
          .then(resp => {
            this.loading = false
            this.listRespBody.total = resp.data.totalElements
            this.listRespBody.roles = resp.data.content
          })
          .catch(() => {
            this.loading = false
          })
      },
      detail (id) {
        store.dispatch('getRole', {roleId: id})
          .then(resp => {
            this.role = resp.data
            this.isShowDetailModal = true
          })
      },
      edit (id) {
        store.dispatch('getRole', {roleId: id})
          .then(resp => {
            this.role = resp.data
            this.isShowEditModal = true
          })
      },
      setResources (id) {
        store.dispatch('preSetRoleResources', {roleId: id})
          .then(resp => {
            this.role = {id: id}
            this.resources = resp.data
            this.isShowRoleResourceSetModal = true
          })
      },
      setUsers (id) {
        this.role = {id: id}
        this.isShowRoleUserModal = true
      },
      del (id) {
        this.$dialog.confirm({
          title: '删除角色',
          message: '确认<b>删除</b> 该角色? 该操作不回复!',
          confirmText: '删除',
          type: 'is-danger',
          hasIcon: true,
          onConfirm: () => {
            store.dispatch('delRole', {roleId: id})
              .then((resp) => {
                this.listRoles()
                this.$toast.open({
                  duration: 5000,
                  message: resp.data,
                  position: 'is-top',
                  type: 'is-warning'
                })
              })
          }
        })
      }
    },
    beforeMount () {
      this.listRoles()
    }
  }
</script>

<style scoped>

</style>
