<template>
  <section>
    <div class="modal-card" style="width: auto">
      <header class="modal-card-head">
        <p class="modal-card-title">设置角色权限</p>
      </header>
      <section class="modal-card-body">
        <v-jstree :data="resources" text-field-name="displayName" value-field-name="id" children-field-name="subs"
                  show-checkbox multiple allow-batch whole-row :collapse="true"
                  @item-click="itemClick"></v-jstree>
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
    name: 'RoleResourceSetModal',
    props: {
      role: {type: Object, required: true},
      resources: {type: Array, required: true}
    },
    methods: {
      itemClick (node) {
        if (node.data.selected) {
          let selectedParent = function (node) {
            if (node.$parent.data) {
              node.$parent.data.selected = true
              selectedParent(node.$parent)
            }
          }
          selectedParent(node)
        } else {
          let unSelectedParent = function (node) {
            if (node.$parent.data && node.$parent.data.subs) {
              let selectedSubsResources = node.$parent.data.subs.filter(e => e.selected)
              if (selectedSubsResources.length === 0) {
                node.$parent.data.selected = false
                unSelectedParent(node.$parent)
              }
            }
          }
          unSelectedParent(node)
        }
      },
      save () {
        let resourceIds = []
        let addSelectedResourceId = function (resources) {
          if (resources) {
            for (let i = 0; i < resources.length; i++) {
              if (resources[i].selected) {
                resourceIds.push(resources[i].id)
                if (resources[i].subs) {
                  addSelectedResourceId(resources[i].subs)
                }
              }
            }
          }
        }
        addSelectedResourceId(this.resources)
        if (resourceIds.length === 0) {
          this.$toast.open({
            duration: 5000,
            message: '请选择角色对应的权限',
            position: 'is-top',
            type: 'is-warning'
          })
          return
        }
        store.dispatch('setRoleResources', {roleId: this.role.id, resourceIds})
          .then(() => {
            this.$parent.close()
          })
      }
    }
  }
</script>

<style scoped>

</style>
