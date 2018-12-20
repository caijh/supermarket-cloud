<template>
  <form>
    <div class="modal-card" style="width: auto">
      <header class="modal-card-head">
        <p class="modal-card-title">门店信息</p>
      </header>
      <section class="modal-card-body">
        <div class="field is-horizontal">
          <div class="field-label is-normal">
            <label class="label">ID</label>
          </div>
          <div class="field-body">
            <div class="field">
              <p class="control is-expanded">
                <input class="input" type="text" :value="shop.id" readonly>
              </p>
            </div>
          </div>
        </div>
        <div class="field is-horizontal">
          <div class="field-label is-normal">
            <label class="label">门店名称</label>
          </div>
          <div class="field-body">
            <div class="field">
              <p class="control is-expanded">
                <input class="input" type="text" :value="shop.name" readonly>
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
    name: 'ShopDetailModal',
    props: {
      shop: {
        type: Object,
        required: true
      }
    }
  }
</script>

<style scoped>

</style>
