<template>
  <section>
    <div class="modal-card" style="width: auto">
      <header class="modal-card-head">
        <p class="modal-card-title">编辑角色</p>
      </header>
      <section class="modal-card-body">
        <div class="field is-horizontal">
          <div class="field-label is-normal">
            <label class="label">角色代号</label>
          </div>
          <div class="field-body">
            <div class="field">
              <p class="control is-expanded">
                <input class="input" type="text" :value="role.code" readonly>
              </p>
            </div>
          </div>
        </div>
        <div class="field is-horizontal">
          <div class="field-label is-normal">
            <label class="label">角色名称</label>
          </div>
          <div class="field-body">
            <div class="field">
              <p class="control is-expanded">
                <input class="input" type="text" :value="role.name">
              </p>
            </div>
          </div>
        </div>
      </section>
      <footer class="modal-card-foot">
        <button class="button" type="button" @click="$parent.close()">Close</button>
        <button class="button is-primary" @click="save()">Save</button>
      </footer>
    </div>
  </section>
</template>

<script>
  import store from '@/store'

  export default {
    name: 'RoleEditModal',
    props: {
      role: {
        type: Object,
        require: true
      }
    },
    methods: {
      save () {
        store.dispatch('updateRole', this.role)
          .then(resp => {
            this.$parent.close()
            this.$toast.open({
              duration: 5000,
              message: resp.data,
              position: 'is-top',
              type: 'is-warning'
            })
          })
      }
    }
  }
</script>

<style scoped>

</style>
