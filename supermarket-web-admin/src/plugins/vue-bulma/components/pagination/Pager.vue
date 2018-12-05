<script>
  export default {
    name: 'Pager',
    props: {
      active: Boolean,
      pageNo: Number
    },
    render (h) {
      const activeClass = this.active ? 'pagination-link is-current' : 'pagination-link'
      return (
        <li><a class={activeClass} onClick={this.$parent.handleChangePage.bind(this, this.pageNo)}>{this.pageNo}</a>
        </li>
      )
    }
  }
</script>
