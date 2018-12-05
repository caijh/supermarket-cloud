<template>
  <form>
    <div class="modal-card" style="width: auto">
      <header class="modal-card-head">
        <p class="modal-card-title">角色详情</p>
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
                <input class="input" type="text" :value="role.name" readonly>
              </p>
            </div>
          </div>
        </div>
      </section>
      <footer class="modal-card-foot">
      </footer>
    </div>
  </form>
</template>

<script>
  export default {
    name: 'role-detail-modal',
    props: {
      role: {
        type: Object,
        required: true
      }
    }
  }
</script>

<style scoped>

</style>
