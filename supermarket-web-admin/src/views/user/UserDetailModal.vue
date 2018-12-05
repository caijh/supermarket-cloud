<template>
  <form>
    <div class="modal-card" style="width: auto">
      <header class="modal-card-head">
        <p class="modal-card-title">用户详情</p>
      </header>
      <section class="modal-card-body">
        <div class="field is-horizontal">
          <div class="field-label is-normal">
            <label class="label">ID</label>
          </div>
          <div class="field-body">
            <div class="field">
              <p class="control is-expanded">
                <input class="input" type="text" :value="user.id" readonly>
              </p>
            </div>
          </div>
        </div>
        <div class="field is-horizontal">
          <div class="field-label is-normal">
            <label class="label">账号</label>
          </div>
          <div class="field-body">
            <div class="field">
              <p class="control is-expanded">
                <input class="input" type="text" :value="user.account" readonly>
              </p>
            </div>
          </div>
        </div>
        <div class="field is-horizontal">
          <div class="field-label is-normal">
            <label class="label">昵称</label>
          </div>
          <div class="field-body">
            <div class="field">
              <p class="control is-expanded">
                <input class="input" type="text" :value="user.nickname" readonly>
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
    name: 'user-detail-modal',
    props: {
      user: {
        type: Object,
        required: true
      }
    }
  }
</script>

<style scoped>

</style>
