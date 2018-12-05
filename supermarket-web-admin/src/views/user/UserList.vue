<template>
  <div>
    <table class="table is-bordered is-striped is-hoverable is-fullwidth">
      <thead>
      <tr>
        <th>用户ID</th>
        <th>用户账号</th>
        <th>昵称</th>
        <th>创建时间</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="item in listRespBody.users" :key="item.id">
        <td>{{item.id}}</td>
        <td>{{item.account}}</td>
        <td>{{item.nickname}}</td>
        <td>{{item.createTime}}</td>
        <td>
          <a @click="detail(item.id)">查看</a>
        </td>
      </tr>
      </tbody>
    </table>
    <b-pagination
      :total="listRespBody.total"
      :current.sync="listRespBody.pageNo"
      :size="'is-small'"
      :order="'is-centered'"
      :per-page="listRespBody.pageSize">
    </b-pagination>
    <b-modal :active.sync="isShowUserDetail">
      <user-detail-modal :user="user"></user-detail-modal>
    </b-modal>
  </div>
</template>

<script>
  import store from '@/store'
  import UserDetailModal from '@/views/user/UserDetailModal'

  export default {
    name: 'user-list',
    components: {UserDetailModal},
    data () {
      return {
        listReqBody: {
          pageNo: 1,
          pageSize: 10
        },
        listRespBody: {
          users: [],
          total: 0
        },
        user: {},
        isShowUserDetail: false
      }
    },
    watch: {
      'listReqBody.pageNo': function (val, oldVal) {
        if (val !== oldVal) {
          store.dispatch('listUser', this.listReqBody)
            .then(resp => {
              this.listRespBody.total = resp.data.totalElements
              this.listRespBody.users = resp.data.content
            })
        }
      }
    },
    methods: {
      detail (id) {
        store.dispatch('getUserDetail', {userId: id})
          .then(resp => {
            this.user = resp.data
            this.isShowUserDetail = true
          })
      }
    },
    beforeMount () {
      store.dispatch('listUser', this.listReqBody)
        .then(resp => {
          this.listRespBody.total = resp.data.totalElements
          this.listRespBody.users = resp.data.content
        })
    }
  }
</script>

<style scoped>

</style>
