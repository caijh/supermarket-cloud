<template>
  <section>
    <b-table :bordered="true" :striped="true" :hoverable="true" :focusable="true" :loading.sync="loading"
             :data="respBody.list"
             :paginated="true" :currentPage.sync="reqBody.pageNo" :perPage="reqBody.pageSize"
             :total="respBody.total">
      <template slot-scope="props">
        <b-table-column field="id" label="ID" width="40" numeric>
          {{ props.row.id }}
        </b-table-column>

        <b-table-column field="name" label="名称">
          {{ props.row.name }}
        </b-table-column>

        <b-table-column field="createTime" label="创建时间" centered>
          <span class="tag is-success">
            {{ props.row.createTime | formatDate }}
          </span>
        </b-table-column>

        <b-table-column field="updateTime" label="更新时间" centered>
          <span class="tag is-success">
            {{ props.row.updateTime | formatDate }}
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
          </p>
        </b-table-column>
      </template>
    </b-table>
    <b-modal :active.sync="isShowDetail">
      <shop-detail-modal :shop="shop"></shop-detail-modal>
    </b-modal>
  </section>
</template>

<script>
  import store from '@/store'
  import ShopDetailModal from '@/views/shop/ShopDetailModal'

  export default {
    name: 'ShopList',
    components: {ShopDetailModal},
    data () {
      return {
        loading: false,
        reqBody: {
          pageNo: 1,
          pageSize: 10
        },
        respBody: {
          list: [],
          total: 0
        },
        isShowDetail: false,
        shop: null
      }
    },
    methods: {
      list () {
        this.loading = true
        store.dispatch('listShop', this.reqBody)
          .then(resp => {
            this.loading = false
            this.respBody.total = resp.data.totalElements
            this.respBody.list = resp.data.content
          })
          .catch(() => {
            this.loading = false
          })
      },
      detail (id) {
        this.shop = null
        store.dispatch('getShop', id)
          .then(resp => {
            this.shop = resp.data
            this.isShowDetail = true
          })
          .catch(() => {
          })
      }
    },
    beforeMount () {
      this.list()
    }
  }
</script>

<style scoped>

</style>
